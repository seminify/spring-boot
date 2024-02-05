package org.seminify.app.service;

import org.seminify.app.dto.GuestbookDTO;
import org.seminify.app.dto.PageRequestDTO;
import org.seminify.app.dto.PageResultDTO;
import org.seminify.app.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    PageResultDTO<Guestbook, GuestbookDTO> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        var entity = Guestbook.builder().gno(dto.getGno()).title(dto.getTitle()).content(dto.getContent())
                .writer(dto.getWriter()).build();
        return entity;
    }

    default GuestbookDTO entityToDTO(Guestbook entity) {
        var dto = GuestbookDTO.builder().gno(entity.getGno()).title(entity.getTitle()).content(entity.getContent())
                .writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();
        return dto;
    }
}
