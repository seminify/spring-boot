package org.seminify.app.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<ENTITY, DTO> {
    private List<DTO> dtoList;
    private int totalPage;
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<Integer> pageList;

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();
        var tempEnd = (int) (Math.ceil(page / (double) size)) * size;
        this.start = tempEnd - size + 1;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        this.prev = start > 1;
        this.next = totalPage > tempEnd;
        this.pageList = IntStream.rangeClosed(start, end).boxed().toList();
    }

    public PageResultDTO(Page<ENTITY> result, Function<ENTITY, DTO> fn) {
        this.dtoList = result.map(fn).toList();
        this.totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }
}
