package org.seminify.app.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.seminify.app.entity.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
}
