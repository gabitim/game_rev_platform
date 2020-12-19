package com.bd.GameRevPlatform;

import com.bd.GameRevPlatform.dao.GameDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@SpringBootTest
class GameRevPlatformApplicationTests {
	private GameDao gameDao;

	@BeforeEach
	void setUp() throws Exception{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("game_rev_db");
		dataSource.setPassword("bunica");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

		gameDao = new GameDao(new JdbcTemplate(dataSource));
	}

	@Test
	void testGame() {
		GameTest gameTests = new GameTest(gameDao);
		gameTests.testGetAllGames();
	}

}
