package org.seminify.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AppApplicationTests {
	@Test
	public void contextLoads() {
		log.info("contextLoads");
	}
}
