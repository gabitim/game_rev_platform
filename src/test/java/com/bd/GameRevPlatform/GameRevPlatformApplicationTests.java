package com.bd.GameRevPlatform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameRevPlatformApplicationTests {
	@Test
	void test() throws Exception {
		GameTest gameTest = new GameTest();
		gameTest.setUp();

		gameTest.testGetAllGames();
	}
}
