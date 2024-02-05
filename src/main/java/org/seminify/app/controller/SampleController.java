package org.seminify.app.controller;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.seminify.app.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("sample")
@Log4j2
public class SampleController {
    @GetMapping("ex1")
    public void ex1() {
        log.info("ex1");
    }

    @GetMapping("ex2")
    public void exModel(Model model) {
        log.info("exModel");
        var list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            var dto = SampleDTO.builder().sno(i).first("First " + i).last("Last " + i).regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).toList();
        model.addAttribute("list", list);
    }
}
