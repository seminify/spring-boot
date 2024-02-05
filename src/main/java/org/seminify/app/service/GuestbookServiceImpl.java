package org.seminify.app.service;

import java.util.function.Function;

import org.seminify.app.dto.GuestbookDTO;
import org.seminify.app.dto.PageRequestDTO;
import org.seminify.app.dto.PageResultDTO;
import org.seminify.app.entity.Guestbook;
import org.seminify.app.repository.GuestbookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
    private final GuestbookRepository repository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("register");
        log.info(dto);
        var entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<Guestbook, GuestbookDTO> getList(PageRequestDTO requestDTO) {
        log.info("getList");
        log.info(requestDTO);
        var pageable = requestDTO.getPageable(Sort.by("gno").descending());
        var result = repository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = this::entityToDTO;
        return new PageResultDTO<>(result, fn);
    }
}
