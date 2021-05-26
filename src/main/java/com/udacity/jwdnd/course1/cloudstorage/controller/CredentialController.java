package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/credentials")
    public String createAndUpdateCredential(@ModelAttribute Credential credential, Authentication authentication, Model model) {
        int userid = userService.getUser(authentication.getName()).getUserid();
        Integer credentialid = credential.getCredentialid();
        String url = credential.getUrl();
        String username = credential.getUsername();
        String key = credentialService.encodedKey();
        String password = credential.getPassword();
        String encryptedPassword = credentialService.encryptCredential(key, password);
        if (credentialid == null) {
            credentialService.insert(new Credential(null, url, username, key, encryptedPassword, userid));
        } else {
            credentialService.update(new Credential(credentialid, url, username, key, encryptedPassword, userid));
        }
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/credentials/delete/{credentialid}")
    public String deleteCredential(@PathVariable int credentialid, Model model) {
        credentialService.delete(credentialid);
        model.addAttribute("success", true);
        return "result";
    }
}
