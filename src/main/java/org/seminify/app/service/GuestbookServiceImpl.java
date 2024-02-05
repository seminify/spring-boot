package org.seminify.app.service;

import org.seminify.app.dto.GuestbookDTO;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
    @Override
    public Long register(GuestbookDTO dto) {
        log.info("register");
        return null;
    }
}
