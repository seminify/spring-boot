package org.seminify.app.service;

import org.seminify.app.dto.GuestbookDTO;
import org.seminify.app.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        var entity = Guestbook.builder().gno(dto.getGno()).title(dto.getTitle()).content(dto.getContent())
                .writer(dto.getWriter()).build();
        return entity;
    }
}
