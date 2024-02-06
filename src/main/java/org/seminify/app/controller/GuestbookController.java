package org.seminify.app.controller;

import org.seminify.app.dto.GuestbookDTO;
import org.seminify.app.dto.PageRequestDTO;
import org.seminify.app.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("register")
    public void register() {
        log.info("reigster");
    }

    @PostMapping("register")
    public String register(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("register");
        log.info(dto);
        var gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list");
        log.info(pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping({ "read", "modify" })
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("read");
        log.info(gno);
        var dto = service.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("modify")
    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes redirectAttributes) {
        log.info("modify");
        log.info(dto);
        service.modify(dto);
        redirectAttributes.addAttribute("gno", dto.getGno());
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        return "redirect:/guestbook/read";
    }

    @PostMapping("remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        log.info("remove");
        log.info(gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }
}
