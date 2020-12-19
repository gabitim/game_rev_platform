package com.bd.GameRevPlatform;

import dao.GameDao;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void testGetAllGames() {
		List<Game> games  = gameDao.getAllGames();

		assertFalse(games.isEmpty());
	}

	@Test
	void testGetGame() {
	}

	@Test
	void testInsertGame() {
	}

	@Test
	void testUpdateGame() {
	}

	@Test
	void testDeleteGame() {
	}


}
