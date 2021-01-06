package com.bd.GameRevPlatform;

import com.bd.GameRevPlatform.dao.*;
import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.model.Userr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserrTest {
    private UserDao userDao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl( "jdbc:oracle:thin:@localhost:1521:xe" );
        dataSource.setUsername( "game_rev_db_new" );
        dataSource.setPassword( "bunica" );
        dataSource.setDriverClassName( "oracle.jdbc.OracleDriver" );

        userDao = new UserDao( new JdbcTemplate( dataSource ) );
    }

    @Test
    void testGetAllUserrs() {
        List< Userr > users = userDao.getAllUsers();
        users.forEach( System.out::print );

        assertFalse( users.isEmpty() );
    }

    @Test
    void testGetUserr() {
        int   userr_id = 4;
        Userr userr    = userDao.getUserrById( userr_id );


        assertNotNull( userr );
    }

    @Test
    void testInsertUserr() {
        Userr userr = new Userr(
                "marius",
                "gavri",
                "e2d4063f0f609706073e6d2572666a577aa250f663ba7616b18902f359cddc1b",
                "sambucelaxinte@gmail.com",
                new java.sql.Date( ( new Date() ).getTime() ),
                4,
                1
        );

        userDao.insertUserr( userr );
    }

    @Test
    void getUserrByCredentials() {
        String hashedPassword = "e2d4063f0f609706073e6d2572666a577aa250f663ba7616b18902f359cddc1b";
        String email          = "sambucelaxinte@gmail.com";
        Userr  userr          = userDao.getUserrByCredentials( hashedPassword, email );

        assertNotNull( userr );
    }

    @Test
    void getUserrByEmail() {
        String email = "sambucelaxinte@gmail.com";
        Userr  userr = userDao.getUserByEmail( email );

        assertNotNull( userr );
    }

    @Test
    void updatePassword() {
        String email             = "sambucelaxinte@gmail.com";
        String newHashedPassword = "3530608a8fa097f2fd72e17a267e46ff84b81eec2025f7ebba256dedc85a21af";

        assertEquals( 1, userDao.updatePassword( email, newHashedPassword ) );
    }

}

