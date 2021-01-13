package com.bd.GameRevPlatform.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */

@Repository
public class GameSessionDao {
    private JdbcTemplate jdbcTemplate;

    public GameSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteGameSession(int game_id) throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("game_rev_db_new");
        dataSource.setPassword("bunica");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        Connection conn = dataSource.getConnection();
        try {
            conn.setAutoCommit(false);

            String sql = "DELETE from GameSession WHERE game_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, game_id);
            st.executeUpdate();

            //conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }
}
