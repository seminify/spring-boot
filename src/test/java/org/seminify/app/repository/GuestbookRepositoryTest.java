package org.seminify.app.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.seminify.app.entity.Guestbook;
import org.seminify.app.entity.QGuestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            var guestbook = Guestbook.builder().title("Title " + i).content("Content " + i).writer("user" + (i % 10))
                    .build();
            log.info(guestbookRepository.save(guestbook));
        });
    }

    @Test
    @Commit
    @Transactional
    public void updateTest() {
        var result = guestbookRepository.findById(300L);
        result.ifPresent(guestbook -> {
            guestbook.setTitle("Changed Title");
            guestbook.setContent("Changed Content");
        });
    }

    @Test
    public void testQuery1() {
        var pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        var guestbook = QGuestbook.guestbook;
        var keyword = "1";
        var builder = new BooleanBuilder();
        var expression = guestbook.title.contains(keyword);
        builder.and(expression);
        var result = guestbookRepository.findAll(builder, pageable);
        result.forEach(log::info);
    }

    @Test
    public void testQuery2() {
        var pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        var guestbook = QGuestbook.guestbook;
        var keyword = "1";
        var builder = new BooleanBuilder();
        var exTitle = guestbook.title.contains(keyword);
        var exContent = guestbook.content.contains(keyword);
        var exAll = exTitle.or(exContent);
        builder.and(exAll);
        builder.and(guestbook.gno.gt(0L));
        var result = guestbookRepository.findAll(builder, pageable);
        result.forEach(log::info);
    }
}
