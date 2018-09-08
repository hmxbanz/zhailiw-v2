package com.zhailiw.app.server.response;

public class CheckWxQqResponse {

    /**
     * state : 1
     * msg : 成功
     * wx : 已绑定
     * qq : 已绑定
     */

    private int state;
    private String msg;
    private String wx;
    private String qq;

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

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
