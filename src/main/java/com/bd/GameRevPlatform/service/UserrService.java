package com.bd.GameRevPlatform.service;

import com.bd.GameRevPlatform.dao.GameDao;
import com.bd.GameRevPlatform.dao.UserDao;
import com.bd.GameRevPlatform.model.Game;
import com.bd.GameRevPlatform.model.PasswordSetter;
import com.bd.GameRevPlatform.model.Userr;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserrService {
    private              UserDao userDao;
    private static final char[]  hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public UserrService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static String byteArray2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer( bytes.length * 2 );
        for (final byte b : bytes) {
            sb.append( hex[( b & 0xF0 ) >> 4] );
            sb.append( hex[b & 0x0F] );
        }
        return sb.toString();
    }

    public static String getStringFromSHA256(String stringToEncrypt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance( "SHA-256" );
        messageDigest.update( stringToEncrypt.getBytes() );
        return byteArray2Hex( messageDigest.digest() );
    }

    public void saveUserr(Userr userr) throws NoSuchAlgorithmException {
        Random rn             = new Random();
        int    groupID        = rn.nextInt( 10 ) + 1;
        String hashedPassword = UserrService.getStringFromSHA256( userr.getHashedPassword() );

        userr.setHashedPassword( hashedPassword );
        userr.setJoinDate( new java.sql.Date( ( new Date() ).getTime() ) );
        userr.setGroup_id( groupID );

        userDao.insertUserr( userr );
    }

    public boolean checkValidLogin(Userr userr) throws NoSuchAlgorithmException {
        String hashedPassword = UserrService.getStringFromSHA256( userr.getHashedPassword() );
        Userr  queryUser      = userDao.getUserrByCredentials( hashedPassword, userr.getEmail() );

        if (queryUser == null) {
            return false;
        }

        return userr.getEmail().equals( queryUser.getEmail() )
                && hashedPassword.equals( queryUser.getHashedPassword() );
    }

    public boolean resetPassword(PasswordSetter setter) throws NoSuchAlgorithmException {
        String hashedPassword = UserrService.getStringFromSHA256( setter.getHashedPassword() );
        Userr  queryUser      = userDao.getUserByEmail( setter.getEmail() );

        if (queryUser == null)
            return false;

        if (!setter.getHashedPassword().equals( setter.getRetypedPassword() ))
            return false;

        if (!setter.getEmail().equals( queryUser.getEmail() ))
            return false;

        int numberOfRowsAffected = userDao.updatePassword( setter.getEmail(), hashedPassword );
        return numberOfRowsAffected == 1;
    }
}
