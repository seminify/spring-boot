package org.seminify.app.dto;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResultDTO<ENTITY, DTO> {
    private List<DTO> dtoList;

    public PageResultDTO(Page<ENTITY> result, Function<ENTITY, DTO> fn) {
        this.dtoList = result.map(fn).toList();
    }
}
