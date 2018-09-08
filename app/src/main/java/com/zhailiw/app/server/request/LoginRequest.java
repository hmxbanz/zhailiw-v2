package com.zhailiw.app.server.request;

public class LoginRequest {

    private String userName;
    private String password;
    private String openId;
    private String type;

    public LoginRequest(String userName, String password,String openId,String type) {
        this.userName = userName;
        this.password = password;
        this.openId = openId;
        this.type = type;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
