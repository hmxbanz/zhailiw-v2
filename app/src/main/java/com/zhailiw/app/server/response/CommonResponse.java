package com.zhailiw.app.server.response;


public class CommonResponse {

    /**
     * state : 1
     * msg : 成功
     */

    private int state;
    private String msg;

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
}
