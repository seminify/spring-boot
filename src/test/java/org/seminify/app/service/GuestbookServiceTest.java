package org.seminify.app.service;

import org.junit.jupiter.api.Test;
import org.seminify.app.dto.GuestbookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookServiceTest {
    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void testRegister() {
        var guestbookDTO = GuestbookDTO.builder().title("Sample Title").content("Sample Content").writer("user0")
                .build();
        log.info(guestbookService.register(guestbookDTO));
    }
}
