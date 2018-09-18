package com.zhailiw.app.server.response;


import java.util.List;

public class HousePeopleResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"DecorateClientID":1,"HouseID":16,"UserInfoID":1475,"Type":null,"CreateDate":null,"NickName":"幸运欣","RealName":"幸运欣","CellPhone":"13729213015         ","RoleName":"设计师","RoleID":13},{"DecorateClientID":3,"HouseID":16,"UserInfoID":1479,"Type":null,"CreateDate":null,"NickName":"昵称","RealName":"工头","CellPhone":"13729213010","RoleName":"监工","RoleID":14},{"DecorateClientID":2,"HouseID":16,"UserInfoID":1478,"Type":null,"CreateDate":null,"NickName":"赵子龙","RealName":"aliy","CellPhone":"15217614631","RoleName":"业务员","RoleID":16}]
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
         * DecorateClientID : 1
         * HouseID : 16
         * UserInfoID : 1475
         * Type : null
         * CreateDate : null
         * NickName : 幸运欣
         * RealName : 幸运欣
         * CellPhone : 13729213015
         * RoleName : 设计师
         * RoleID : 13
         */

        private int DecorateClientID;
        private int HouseID;
        private int UserInfoID;
        private Object Type;
        private Object CreateDate;
        private String NickName;
        private String RealName;
        private String CellPhone;
        private String RoleName;
        private int RoleID;

        public int getDecorateClientID() {
            return DecorateClientID;
        }

        public void setDecorateClientID(int DecorateClientID) {
            this.DecorateClientID = DecorateClientID;
        }

        public int getHouseID() {
            return HouseID;
        }

        public void setHouseID(int HouseID) {
            this.HouseID = HouseID;
        }

        public int getUserInfoID() {
            return UserInfoID;
        }

        public void setUserInfoID(int UserInfoID) {
            this.UserInfoID = UserInfoID;
        }

        public Object getType() {
            return Type;
        }

        public void setType(Object Type) {
            this.Type = Type;
        }

        public Object getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(Object CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public String getCellPhone() {
            return CellPhone;
        }

        public void setCellPhone(String CellPhone) {
            this.CellPhone = CellPhone;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public int getRoleID() {
            return RoleID;
        }

        public void setRoleID(int RoleID) {
            this.RoleID = RoleID;
        }
    }
}
