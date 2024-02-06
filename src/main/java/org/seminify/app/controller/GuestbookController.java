package org.seminify.app.controller;

import org.seminify.app.dto.PageRequestDTO;
import org.seminify.app.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("guestbook")
@RequiredArgsConstructor
@Log4j2
public class GuestbookController {
    private final GuestbookService service;

    @GetMapping("/")
    public String index() {
        log.info("index");
        return "redirect:/guestbook/list";
    }

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list");
        log.info(pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
