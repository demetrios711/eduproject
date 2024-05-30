package org.euproject.eduproject.Controller;

import java.util.List;

import org.euproject.eduproject.models.Document;
import org.euproject.eduproject.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private DocumentService documentService;

    @GetMapping("/")
    public String home(Model model)
    {
        List<Document> documents = documentService.getAll();
        model.addAttribute("documents", documents);
        return "home";
    }

}
