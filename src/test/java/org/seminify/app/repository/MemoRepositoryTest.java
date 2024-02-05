package org.seminify.app.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.seminify.app.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemoRepositoryTest {
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testClass() {
        log.info(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            var memo = Memo.builder().memoText("Sample " + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        var mno = 100L;
        var result = memoRepository.findById(mno);
        result.ifPresent(log::info);
    }

    @Test
    @Transactional(readOnly = true)
    public void testSelect2() {
        var mno = 100L;
        var memo = memoRepository.getOne(mno);
        log.info(memo);
    }

    @Test
    public void testUpdate() {
        var memo = Memo.builder().mno(100L).memoText("Update Text").build();
        log.info(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        var mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        var pageable = PageRequest.of(0, 10);
        var result = memoRepository.findAll(pageable);
        log.info(result);
        log.info("Total Pages : " + result.getTotalPages());
        log.info("Total Count : " + result.getTotalElements());
        log.info("Page Number : " + result.getNumber());
        log.info("Page Size : " + result.getSize());
        log.info("Has Next Page? : " + result.hasNext());
        log.info("First Page? : " + result.isFirst());
        result.forEach(log::info);
    }

    @Test
    public void testSort() {
        var sort1 = Sort.by("mno").descending();
        var sort2 = Sort.by("memoText").ascending();
        var sortAll = sort1.and(sort2);
        var pageable1 = PageRequest.of(0, 10, sort1);
        var pageable2 = PageRequest.of(0, 10, sortAll);
        var result1 = memoRepository.findAll(pageable1);
        var result2 = memoRepository.findAll(pageable2);
        result1.forEach(log::info);
        result2.forEach(log::info);
    }

    @Test
    public void testQueryMethod() {
        var list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        list.forEach(log::info);
    }

    @Test
    public void testQueryMethodWithPageable() {
        var pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        var result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.forEach(log::info);
    }

    @Test
    @Commit
    @Transactional
    public void testDeleteQueryMethod() {
        memoRepository.deleteByMnoLessThan(10L);
    }
}
