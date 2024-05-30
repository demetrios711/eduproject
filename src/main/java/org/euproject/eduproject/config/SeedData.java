package org.euproject.eduproject.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.euproject.eduproject.models.Account;
import org.euproject.eduproject.models.Authority;
import org.euproject.eduproject.models.Document;
import org.euproject.eduproject.services.AccountService;
import org.euproject.eduproject.services.AuthorityService;
import org.euproject.eduproject.services.DocumentService;
import org.euproject.eduproject.util.constants.Privileges;
import org.euproject.eduproject.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        List<Document> documents = documentService.getAll();

        for (Privileges auth : Privileges.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivilege());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();


        account01.setEmail("mod@mod.com");
        account01.setPassword("mod");
        account01.setUsername("moduser");
        account01.setCountry("Greece, GR");
        account01.setRole(Roles.MODERATOR.getRole());

        account02.setEmail("admin@admin.com");
        account02.setPassword("admin");
        account02.setUsername("adminuser");
        account02.setCountry("Zimbabwe, ZW");
        account02.setRole(Roles.ADMIN.getRole());
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privileges.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);;
        authorityService.findById(Privileges.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);;
        account02.setAuthorities(authorities);

        // Default Test User
        account03.setEmail("user@user.com");
        account03.setPassword("user");
        account03.setUsername("useruser");
        account03.setCountry("China, CN");

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);


        if (documents.size() == 0) {
            Document document01 = new Document();
            document01.setCertification("Google Certification");
            document01.setComments("Completed 3/11/1973");
            document01.setAccount(account01);
            documentService.save(document01);

            Document document02 = new Document();
            document02.setCertification("English Language Cert.");
            document02.setComments("Completed 3/11/1973");
            document02.setAccount(account01);
            documentService.save(document02);

            Document document03 = new Document();
            document03.setCertification("Physics Degree");
            document03.setComments("Completed 3/11/1973");
            document03.setAccount(account02);
            documentService.save(document03);

            Document document04 = new Document();
            document04.setCertification("Squirrel Degree");
            document04.setComments("Completed 3/11/1973");
            document04.setAccount(account02);
            documentService.save(document04);

        }

    }

}
