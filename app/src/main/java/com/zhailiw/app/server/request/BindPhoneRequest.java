package com.zhailiw.app.server.request;

public class BindPhoneRequest {

    private String cellphone;
    private String captcha;
    private String openId;
    private String type;

    public BindPhoneRequest(String cellphone, String captcha, String openId,String type) {
        this.cellphone = cellphone;
        this.captcha = captcha;
        this.openId = openId;
        this.type = type;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
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
