package org.dipl.rarefashion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping(path = "/")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails != null) {
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                return "redirect:/admin";
            }
        }

        model.addAttribute("activeMenu", "home");
        return "home";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }
}
