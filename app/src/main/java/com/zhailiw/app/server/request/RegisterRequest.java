package com.zhailiw.app.server.request;

public class RegisterRequest {

    private String userName;
    private String password;
    private String nickName;
    private String captcha;

    public RegisterRequest(String userName, String password,String nickName, String captcha) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.captcha = captcha;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
