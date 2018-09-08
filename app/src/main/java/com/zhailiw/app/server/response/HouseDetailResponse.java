package com.zhailiw.app.server.response;


public class HouseDetailResponse {

    /**
     * state : 1
     * msg : 成功
     * data : {"CreateDate":null,"HousePic":null,"HouseName":"四合院","HouseId":16,"ClientId":9,"Size":"128","Budget":125000,"Address":"广东省汕头市澄海区广益街道","Remark":"冠山高级住宅区明星专属\b度假休闲旅游圣地","IsTop":null,"UserInfoId":1478,"CellPhone":"15217614635","Name":"张靓颖2","HouseTypeId":null,"HouseWorkId":null,"HouseTypeName":null,"HouseWorkName":null}
     */

    private int state;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * CreateDate : null
         * HousePic : null
         * HouseName : 四合院
         * HouseId : 16
         * ClientId : 9
         * Size : 128
         * Budget : 125000
         * Address : 广东省汕头市澄海区广益街道
         * Remark : 冠山高级住宅区明星专属度假休闲旅游圣地
         * IsTop : null
         * UserInfoId : 1478
         * CellPhone : 15217614635
         * Name : 张靓颖2
         * HouseTypeId : null
         * HouseWorkId : null
         * HouseTypeName : null
         * HouseWorkName : null
         */

        private Object CreateDate;
        private Object HousePic;
        private String HouseName;
        private int HouseId;
        private int ClientId;
        private String Size;
        private int Budget;
        private String Address;
        private String Remark;
        private Object IsTop;
        private int UserInfoId;
        private String CellPhone;
        private String Name;
        private int HouseTypeId;
        private int HouseWorkId;
        private String HouseTypeName;
        private String HouseWorkName;

        public Object getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(Object CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getHousePic() {
            return HousePic;
        }

        public void setHousePic(Object HousePic) {
            this.HousePic = HousePic;
        }

        public String getHouseName() {
            return HouseName;
        }

        public void setHouseName(String HouseName) {
            this.HouseName = HouseName;
        }

        public int getHouseId() {
            return HouseId;
        }

        public void setHouseId(int HouseId) {
            this.HouseId = HouseId;
        }

        public int getClientId() {
            return ClientId;
        }

        public void setClientId(int ClientId) {
            this.ClientId = ClientId;
        }

        public String getSize() {
            return Size;
        }

        public void setSize(String Size) {
            this.Size = Size;
        }

        public int getBudget() {
            return Budget;
        }

        public void setBudget(int Budget) {
            this.Budget = Budget;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public Object getIsTop() {
            return IsTop;
        }

        public void setIsTop(Object IsTop) {
            this.IsTop = IsTop;
        }

        public int getUserInfoId() {
            return UserInfoId;
        }

        public void setUserInfoId(int UserInfoId) {
            this.UserInfoId = UserInfoId;
        }

        public String getCellPhone() {
            return CellPhone;
        }

        public void setCellPhone(String CellPhone) {
            this.CellPhone = CellPhone;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getHouseTypeId() {
            return HouseTypeId;
        }

        public void setHouseTypeId(int HouseTypeId) {
            this.HouseTypeId = HouseTypeId;
        }

        public Object getHouseWorkId() {
            return HouseWorkId;
        }

        public void setHouseWorkId(int HouseWorkId) {
            this.HouseWorkId = HouseWorkId;
        }

        public String getHouseTypeName() {
            return HouseTypeName;
        }

        public void setHouseTypeName(String HouseTypeName) {
            this.HouseTypeName = HouseTypeName;
        }

        public String getHouseWorkName() {
            return HouseWorkName;
        }

        public void setHouseWorkName(String HouseWorkName) {
            this.HouseWorkName = HouseWorkName;
        }
    }
}
