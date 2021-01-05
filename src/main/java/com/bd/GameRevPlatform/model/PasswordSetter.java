package com.bd.GameRevPlatform.model;

public class PasswordSetter {
    private String hashedPassword;
    private String email;
    private String retypedPassword;

    public PasswordSetter(String email, String hashedPassword, String retypedPassword) {
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.retypedPassword = retypedPassword;
    }

    public PasswordSetter() {
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }
}
