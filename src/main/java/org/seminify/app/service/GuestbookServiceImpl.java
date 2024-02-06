package org.seminify.app.service;

import java.util.function.Function;

import org.seminify.app.dto.GuestbookDTO;
import org.seminify.app.dto.PageRequestDTO;
import org.seminify.app.dto.PageResultDTO;
import org.seminify.app.entity.Guestbook;
import org.seminify.app.entity.QGuestbook;
import org.seminify.app.repository.GuestbookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
    private final GuestbookRepository repository;

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        var booleanBuilder = new BooleanBuilder();
        var type = requestDTO.getType();
        var keyword = requestDTO.getKeyword();
        var guestbook = QGuestbook.guestbook;
        var expression = guestbook.gno.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0)
            return booleanBuilder;
        var conditionBuilder = new BooleanBuilder();
        if (type.contains("t"))
            conditionBuilder.or(guestbook.title.contains(keyword));
        if (type.contains("c"))
            conditionBuilder.or(guestbook.content.contains(keyword));
        if (type.contains("w"))
            conditionBuilder.or(guestbook.writer.contains(keyword));
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

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
        var booleanBuilder = getSearch(requestDTO);
        var pageable = requestDTO.getPageable(Sort.by("gno").descending());
        var result = repository.findAll(booleanBuilder, pageable);
        Function<Guestbook, GuestbookDTO> fn = this::entityToDTO;
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        log.info("read");
        log.info(gno);
        var result = repository.findById(gno);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void modify(GuestbookDTO dto) {
        log.info("modify");
        log.info(dto);
        var result = repository.findById(dto.getGno());
        result.ifPresent(entity -> {
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            repository.save(entity);
        });
    }

    @Override
    public void remove(Long gno) {
        log.info("remove");
        log.info(gno);
        repository.deleteById(gno);
    }
}
