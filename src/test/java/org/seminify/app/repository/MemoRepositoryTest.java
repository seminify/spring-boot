package org.seminify.app.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.seminify.app.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
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
}
