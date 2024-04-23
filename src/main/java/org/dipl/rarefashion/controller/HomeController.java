package org.dipl.rarefashion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

/*
    private final EventsRepository eventsRepository;
    private final Utils utils;
*/

    @GetMapping(path = "/")
    public String index(Model model) {

/*
        String title = utils.getMessage("home.title") + " - " + utils.getMessage("app.title");
        model.addAttribute("activeMenu", "home");
        model.addAttribute("activeEventsList", eventsRepository.findActiveEvents());
        model.addAttribute("ogAttributes", OgAttributes.builder()
                .image("og_image_ih.png")
                .title(title)
                .description(utils.getMessage("home.metadescr"))
                .url("https://innerharmony.bg")
                .build());
        model.addAttribute("richcardJson", utils.getRichCardJSON(BlogPosting.builder()
                .headline(title)
                .images(new String[]{"https://innerharmony.bg/img/subconscious-work.png",
                        "https://innerharmony.bg/img/reiki.png",
                        "https://innerharmony.bg/img/innerdance.png",
                        "https://innerharmony.bg/img/bioresonance.png",
                        "https://innerharmony.bg/img/radi.png",
                        "https://innerharmony.bg/img/hooponopono.png"})
                .build()));
*/

        return "home";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }





    /*

    @GetMapping(path = "/prices")
    public String prices(Model model) {

        String title = utils.getMessage("prices.title") + " - " + utils.getMessage("app.title");
        model.addAttribute("activeMenu", "prices");
        model.addAttribute("ogAttributes", OgAttributes.builder()
                .image("og_image_ih.png")
                .title(title)
                .description(utils.getMessage("prices.metadescr"))
                .url("https://innerharmony.bg/prices")
                .build());
        model.addAttribute("richcardJson", utils.getRichCardJSON(BlogPosting.builder()
                .headline(title)
                .images(new String[]{"https://innerharmony.bg/img/og_image_ih.png"})
                .build()));

        return "prices";
    }

    @GetMapping(path = "/aboutus")
    public String aboutUs(Model model) {

        String title = utils.getMessage("aboutus.title") + " - " + utils.getMessage("app.title");
        model.addAttribute("activeMenu", "aboutus");
        model.addAttribute("ogAttributes", OgAttributes.builder()
                .image("og_image_ih.png")
                .title(title)
                .description(utils.getMessage("aboutus.metadescr"))
                .url("https://innerharmony.bg/aboutus")
                .build());
        model.addAttribute("richcardJson", utils.getRichCardJSON(BlogPosting.builder()
                .headline(title)
                .images(new String[]{"https://innerharmony.bg/img/katerina.png",
                        "https://innerharmony.bg/img/rumen.png"})
                .build()));

        return "aboutus";
    }

*/
}
