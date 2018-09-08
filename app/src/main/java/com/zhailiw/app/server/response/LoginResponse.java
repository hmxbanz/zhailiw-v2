package com.zhailiw.app.server.response;


/**
 * Created by AMing on 15/12/24.
 * Company RongCloud
 */
public class LoginResponse {

    /**
     * state : 1
     * msg : 成功
     * token : 6db21e34808543dca79366608b81dfee
     */

    private int state;
    private String msg;
    private String token;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
