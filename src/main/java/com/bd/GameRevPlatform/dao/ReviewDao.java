package com.bd.GameRevPlatform.dao;

import com.bd.GameRevPlatform.model.Review;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */



@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM Review WHERE parent_id IS NULL";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public List<Review> getAllReviewsAndComments() {
        String sql = "SELECT * FROM Review";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public Review getReview(int review_id){
        String sql = "SELECT * FROM Review WHERE review_id = ?";
        Object[] args = {review_id};

        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public List<Review> getReviewsByGameId(int game_id){
        String sql = "SELECT * FROM Review WHERE game_id = ? AND parent_id IS NULL";
        Object[] args = {game_id};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public List<Review> getReviewsByUserId(int user_id){
        String sql = "SELECT * FROM Review WHERE user_id = ? AND parent_id IS NULL";
        Object[] args = {user_id};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public List<Review> getCommentsByParentId(int parent_id){
        String sql = "SELECT * FROM Review WHERE parent_id = ?";
        Object[] args = {parent_id};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public List<Review> getCommentsByUserId(int user_id) {
        String sql = "SELECT * FROM Review WHERE user_id = ? AND parent_id IS NOT NULL";
        Object[] args = {user_id};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Review.class));
    }

    public void insertReview(Review review) {
        String insertSql = "INSERT INTO Review (title, text_field, game_id, user_id, parent_id, posted_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int review_id = 0;

        // use KeyHolder and PreparedStatement to get the autoincrement id from oracle db
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertSql, new String[] { "review_id" });
            ps.setString(1, review.getTitle());
            ps.setString(2, review.getText_field());
            ps.setInt(3, review.getGame_id());
            ps.setInt(4, review.getUser_id());
            ps.setNull(5, java.sql.Types.INTEGER);
            ps.setDate(6, new java.sql.Date(review.getPosted_date().getTime()));
            return ps;
        }, keyHolder);
        review_id = keyHolder.getKey().intValue();

        review.setReview_id(review_id);
    }

    public void insertComment(Review review) {
        String insertSql = "INSERT INTO Review (title, text_field, game_id, user_id, parent_id, posted_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int review_id = 0;

        // use KeyHolder and PreparedStatement to get the autoincrement id from oracle db
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertSql, new String[] { "review_id" });
            ps.setString(1, review.getTitle());
            ps.setString(2, review.getText_field());
            ps.setInt(3, review.getGame_id());
            ps.setInt(4, review.getUser_id());
            ps.setInt(5, review.getParent_id());
            ps.setDate(6, new java.sql.Date(review.getPosted_date().getTime()));
            return ps;
        }, keyHolder);
        review_id = keyHolder.getKey().intValue();

        review.setReview_id(review_id);
    }

    public void updateReview(Review review) {
        String sql = "UPDATE Review SET title=:title, text_field=:text_field, user_id=:user_id," +
                "posted_date=:posted_date WHERE review_id=:review_id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(review);
        NamedParameterJdbcTemplate temp = new NamedParameterJdbcTemplate(jdbcTemplate);

        temp.update(sql, param);
    }

    public void deleteReviewsByGameId(int game_id) throws SQLException {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("game_rev_db_new");
        dataSource.setPassword("bunica");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        Connection conn = dataSource.getConnection();
        try {
            conn.setAutoCommit(false);

            String sql = "DELETE from Review WHERE game_id = ?";
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

    public void deleteCommentsByParentId(int parent_id) {
        String sql = "DELETE from Review WHERE parent_id = ?";
        jdbcTemplate.update(sql, parent_id);
    }

    public void deleteReview(int review_id) {
        String sql = "DELETE from Review WHERE review_id = ?";
        jdbcTemplate.update(sql, review_id);

    }
}
