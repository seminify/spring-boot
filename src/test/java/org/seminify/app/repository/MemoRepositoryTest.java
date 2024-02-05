package org.seminify.app.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.seminify.app.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
