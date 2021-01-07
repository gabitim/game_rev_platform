package com.bd.GameRevPlatform.service;


import com.bd.GameRevPlatform.dao.UserDao;
import com.bd.GameRevPlatform.model.PasswordSetter;
import com.bd.GameRevPlatform.model.Userr;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserrService {
    private UserDao userDao;
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public UserrService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static String byteArray2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (final byte b : bytes) {
            sb.append(hex[(b & 0xF0) >> 4]);
            sb.append(hex[b & 0x0F]);
        }
        return sb.toString();
    }

    public static String getStringFromSHA256(String stringToEncrypt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(stringToEncrypt.getBytes());
        return byteArray2Hex(messageDigest.digest());
    }

    public void saveUserr(Userr userr) throws NoSuchAlgorithmException {
        Random rn = new Random();
        int groupID = rn.nextInt(10) + 1;
        String hashedPassword = UserrService.getStringFromSHA256(userr.getHashedPassword());

        userr.setHashedPassword(hashedPassword);
        userr.setJoinDate(new java.sql.Date((new Date()).getTime()));
        userr.setGroup_id(groupID);

        userDao.insertUserr(userr);
    }

    public boolean checkValidLogin(Userr userr) throws NoSuchAlgorithmException {
        String hashedPassword = UserrService.getStringFromSHA256(userr.getHashedPassword());
        Userr queryUser = userDao.getUserrByCredentials(hashedPassword, userr.getEmail());

        if (queryUser == null) {
            return false;
        }

        return userr.getEmail().equals(queryUser.getEmail())
                && hashedPassword.equals(queryUser.getHashedPassword());
    }

    public boolean resetPassword(PasswordSetter setter) throws NoSuchAlgorithmException {
        String hashedPassword = UserrService.getStringFromSHA256(setter.getHashedPassword());
        Userr queryUser = userDao.getUserByEmail(setter.getEmail());

        if (queryUser == null)
            return false;

        if (!setter.getHashedPassword().equals(setter.getRetypedPassword()))
            return false;

        if (!setter.getEmail().equals(queryUser.getEmail()))
            return false;

        int numberOfRowsAffected = userDao.updatePassword(setter.getEmail(), hashedPassword);
        return numberOfRowsAffected == 1;
    }

    public Userr getUserById(int user_id) {
        if (user_id != 0) {
            int realUser_id = decrypt(user_id);
            Userr user = userDao.getUserrById(realUser_id);
            user.setUser_id(user_id);
            return user;
        } else {
            // to complete with dummy guest
            return new Userr();
        }
    }

    public int getUserId(String rawHashedPassword, String email) throws NoSuchAlgorithmException {
        String hashedPassword = UserrService.getStringFromSHA256(rawHashedPassword);
        //convert real user_id to random user_id
        int realUser_id = userDao.getUserIdByCredentials(hashedPassword, email);
        return encrypt(realUser_id);
    }

    public int encrypt(int realUser_id) {
        int randomUser_id = ThreadLocalRandom.current().nextInt(100_000, 999_999);

        try {
            FileWriter writer = new FileWriter("src/main/java/com/bd/GameRevPlatform/service/user/user_data.txt");
            writer.write(randomUser_id + ":" + realUser_id);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred writing user data to file");
            e.printStackTrace();
        }

        return randomUser_id;
    }

    public int decrypt(int randomUser_id) {
        try {
            File file = new File("src/main/java/com/bd/GameRevPlatform/service/user/user_data.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                System.out.println(data);
                String[] pair = data.split(":");

                if (Integer.parseInt(pair[0]) == randomUser_id) {
                    return Integer.parseInt(pair[1]);
                }
                else {
                    return 0;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading user data");
            e.printStackTrace();
        }
        return 0;
    }
}
