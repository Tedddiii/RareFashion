package org.dipl.rarefashion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

/*
    private final EventsService eventsService;
    private final EnrollRepository enrollRepository;
    private final Utils utils;
*/

    @GetMapping(path = "/products")
    public String general(Model model) {

        model.addAttribute("activeMenu", "general");

        return "admin/products";
    }

/*
    @GetMapping(path = "/events")
    public String events(@RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                         @RequestParam(name = "i", defaultValue = "5") Integer itemsPerPage,
                         @ModelAttribute("filterForm") EventsFilter filterForm,
                         Model model) {

        utils.initEventsFilter(filterForm);
        Pageable pageable = PageRequest.of(pageIndex - 1, itemsPerPage);
        PageWrapper<EventDto> pageWrp = new PageWrapper<>(eventsService.getEventsDtoWithEnrollments(pageable), null);
        model.addAttribute("page", pageWrp);
        model.addAttribute("activeMenu", "events");

        return "admin/events";
    }

    @GetMapping(path = "/events/prepareDeleteEnroll")
    public String prepareDeleteEnroll(@RequestParam(name = "enrollId") Integer enrollId, Model model) {

        log.info("Preparing delete enroll modal for enrollId: {}", enrollId);

        Optional<Enroll> enroll = enrollRepository.findById(enrollId);
        String msg;

        if (enroll.isEmpty()) {
            log.info("Enrollment not found for deletion: {}", enrollId);
            msg = utils.getMessage("enroll.delete.notfound");
        } else {
            Event event = eventsService.getEvent(enroll.get().getEventId());
            msg = utils.getMessage("enroll.delete.sure", enroll.get().getName(), event.getTitle());
            model.addAttribute("eventId", event.getEventId());
        }

        model.addAttribute("deleteMessage", msg);
        model.addAttribute("enrollId", enrollId);
        return "admin/fragments :: #divModalDeleteEnrollContent";
    }

    @DeleteMapping(path = "/events/deleteEnroll")
    @ResponseBody
    public String deleteEnroll(@RequestParam(name = "enrollId") Integer enrollId) {

        log.info("Deleting enrollId: {}", enrollId);

        Optional<Enroll> enroll = enrollRepository.findById(enrollId);

        if (enroll.isPresent()) {
            enrollRepository.deleteById(enrollId);
            return enroll.get().getEventId();
        }

        return "OK";
    }
*/
}
