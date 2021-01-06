package com.bd.GameRevPlatform.dao;


import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.model.Userr;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    public List< Userr > getAllUsers() { // for user-listing purposes
        String sql = "SELECT * FROM Userr";
        return jdbcTemplate.query( sql, BeanPropertyRowMapper.newInstance( Userr.class ) );
    }

    public Userr getUserrById(int userr_id) { // for log-in purposes
        String   sql  = "SELECT * FROM Userr WHERE user_id = ?";
        Object[] args = { userr_id };

        return jdbcTemplate.queryForObject( sql, args, BeanPropertyRowMapper.newInstance( Userr.class ) );
    }

    public Userr getUserrByCredentials(String hashedPassword, String email) { // for log-in purposes
        String   sql  = "SELECT * FROM Userr WHERE EMAIL = ? and HASHEDPASSWORD = ?";
        Object[] args = { email, hashedPassword };
        Userr    result;

        try {
            result = jdbcTemplate.queryForObject( sql, args, BeanPropertyRowMapper.newInstance( Userr.class ) );
        } catch ( EmptyResultDataAccessException e ) {
            System.out.println( e.getMessage() );
            return null;
        }

        return result;
    }

    public int getUserIdByCredentials(String hashedPassword, String email) {

        String sql  = "SELECT user_id FROM Userr WHERE EMAIL = ? and HASHEDPASSWORD = ?";
        Object[] args = { email, hashedPassword };
        int id = Objects.requireNonNull(jdbcTemplate.queryForObject(sql, args, Integer.class));
        return id;
    }

    public Userr getUserByEmail(String email) { // for log-in purposes
        String   sql  = "SELECT * FROM Userr WHERE email = ?";
        Object[] args = { email };
        Userr    result;

        try {
            result = jdbcTemplate.queryForObject( sql, args, BeanPropertyRowMapper.newInstance( Userr.class ) );
        } catch ( EmptyResultDataAccessException e ) {
            return null;
        }

        return result;
    }

    public void insertUserr(Userr userr) { // for register purposes
        String insertSql = "INSERT INTO Userr(first_name, last_name, hashedPassword, email, join_date, group_id, role_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int       userr_id  = 0;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( conn -> {
            PreparedStatement ps = conn.prepareStatement(
                    insertSql,
                    new String[]{ "user_id" }
            );

            ps.setString( 1, userr.getFirstName() );
            ps.setString( 2, userr.getLastName() );
            ps.setString( 3, userr.getHashedPassword() );
            ps.setString( 4, userr.getEmail() );
            ps.setDate( 5, userr.getJoinDate() );
            ps.setInt( 6, userr.getGroup_id() ); // to be changed
            ps.setInt( 7, userr.getRole_id() ); // to be changed
            return ps;
        }, keyHolder );

        userr_id = keyHolder.getKey().intValue();
        userr.setUser_id( userr_id );
    }

    public int updatePassword(String email, String hashedPassword) {
        String   sql    = "UPDATE Userr set HASHEDPASSWORD = ? where EMAIL = ?";
        Object[] args   = { hashedPassword, email };
        int      result = 0;

        try {
            result = jdbcTemplate.update( sql, args );
        } catch ( EmptyResultDataAccessException e ) {
            System.out.println( e.getMessage() );
            return result;
        }

        return result;
    }
}
