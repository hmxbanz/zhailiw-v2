package com.zhailiw.app.server.response;

import java.util.List;

public class AddressResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"AddressID":2,"Contact":"本人","Cellphone":"本手机","Address":"本地","CreateDate":"/Date(1525074522430)/","Type":null,"UserInfoID":1477},{"AddressID":3,"Contact":"2222","Cellphone":"2222","Address":"222","CreateDate":"/Date(1525074553877)/","Type":null,"UserInfoID":1477},{"AddressID":4,"Contact":"333333333333","Cellphone":"33333333333","Address":"33","CreateDate":"/Date(1525074606437)/","Type":null,"UserInfoID":1477},{"AddressID":5,"Contact":"新","Cellphone":"新","Address":"新","CreateDate":"/Date(1525225642647)/","Type":null,"UserInfoID":1477},{"AddressID":6,"Contact":"","Cellphone":"","Address":"","CreateDate":"/Date(1525230958240)/","Type":null,"UserInfoID":1477},{"AddressID":7,"Contact":"ttttttttttt","Cellphone":"ttttttttttt","Address":"ttttttttt","CreateDate":"/Date(1525230967637)/","Type":1,"UserInfoID":1477}]
     */

    private int state;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * AddressID : 2
         * Contact : 本人
         * Cellphone : 本手机
         * Address : 本地
         * CreateDate : /Date(1525074522430)/
         * Type : null
         * UserInfoID : 1477
         */

        private int AddressID;
        private String Contact;
        private String Cellphone;
        private String Address;
        private String CreateDate;
        private int Type;
        private int UserInfoID;

        public int getAddressID() {
            return AddressID;
        }

        public void setAddressID(int AddressID) {
            this.AddressID = AddressID;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public String getCellphone() {
            return Cellphone;
        }

        public void setCellphone(String Cellphone) {
            this.Cellphone = Cellphone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getUserInfoID() {
            return UserInfoID;
        }

        public void setUserInfoID(int UserInfoID) {
            this.UserInfoID = UserInfoID;
        }
    }
}
