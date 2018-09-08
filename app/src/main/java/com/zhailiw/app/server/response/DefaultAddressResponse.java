package com.zhailiw.app.server.response;


public class DefaultAddressResponse {

    /**
     * state : 1
     * msg : 成功
     * defaultAddress : {"AddressID":19,"Contact":"黄茂欣","Cellphone":"13729213015","Address":"广东省汕头市龙湖区龙眼南路中间","CreateDate":"/Date(1526745638127)/","Type":1,"UserInfoID":1475,"IsDelete":null}
     */

    private int state;
    private String msg;
    private DefaultAddressBean defaultAddress;

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

    public DefaultAddressBean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(DefaultAddressBean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public static class DefaultAddressBean {
        /**
         * AddressID : 19
         * Contact : 黄茂欣
         * Cellphone : 13729213015
         * Address : 广东省汕头市龙湖区龙眼南路中间
         * CreateDate : /Date(1526745638127)/
         * Type : 1
         * UserInfoID : 1475
         * IsDelete : null
         */

        private int AddressID;
        private String Contact;
        private String Cellphone;
        private String Address;
        private String CreateDate;
        private int Type;
        private int UserInfoID;
        private Object IsDelete;

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

        public Object getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(Object IsDelete) {
            this.IsDelete = IsDelete;
        }
    }
}
