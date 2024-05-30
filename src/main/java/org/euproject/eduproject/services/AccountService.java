package org.euproject.eduproject.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.euproject.eduproject.models.Account;
import org.euproject.eduproject.models.Authority;
import org.euproject.eduproject.repositories.AccountRepository;
import org.euproject.eduproject.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService implements UserDetailsService{
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRole() == null){
        account.setRole(Roles.USER.getRole());
        }
        return accountRepository.save(account);
        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> optionalAccount = accountRepository.findByEmailIgnoreCase(email);
        if(!optionalAccount.isPresent()){
            throw new UsernameNotFoundException("Account not found");
        }

        Account account = optionalAccount.get();
        
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));
        
        for(Authority _auth: account.getAuthorities()){
            grantedAuthority.add(new SimpleGrantedAuthority(_auth.getName()));
        }
        return new User(account.getEmail(), account.getPassword(), grantedAuthority);


        // throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }


}
