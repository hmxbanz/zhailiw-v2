package com.zhailiw.app.server.request;

public class AddAddressRequest {

    private String contact;
    private String cellphone;
    private String address;
    private Boolean setDefault;

    public AddAddressRequest(String contact, String cellphone, String address, Boolean checked) {
        this.contact=contact;
        this.cellphone=cellphone;
        this.address=address;
        this.setDefault=checked;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSetDefault() {
        return setDefault;
    }

    public void setSetDefault(Boolean setDefault) {
        this.setDefault = setDefault;
    }
}
