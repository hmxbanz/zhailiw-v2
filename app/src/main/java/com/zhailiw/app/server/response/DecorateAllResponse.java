package com.zhailiw.app.server.response;


import java.util.List;

public class DecorateAllResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 1
     * data : [{"HouseName":"四合院","CreateDate":null,"EstateId":null,"ClientId":9,"Size":"128","Budget":125000,"Address":"广东省汕头市澄海区广益街道","State":318,"UserDesingerID":1475,"UserSellerID":1478,"UserWorkerID":1480,"HouseId":16}]
     */

    private int state;
    private String msg;
    private int totalPages;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * HouseName : 四合院
         * CreateDate : null
         * EstateId : null
         * ClientId : 9
         * Size : 128
         * Budget : 125000
         * Address : 广东省汕头市澄海区广益街道
         * State : 318
         * UserDesingerID : 1475
         * UserSellerID : 1478
         * UserWorkerID : 1480
         * HouseId : 16
         */

        private String HouseName;
        private Object CreateDate;
        private Object EstateId;
        private int ClientId;
        private String Size;
        private int Budget;
        private String Address;
        private int State;
        private int UserDesingerID;
        private int UserSellerID;
        private int UserWorkerID;
        private int HouseId;

        public String getHouseName() {
            return HouseName;
        }

        public void setHouseName(String HouseName) {
            this.HouseName = HouseName;
        }

        public Object getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(Object CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getEstateId() {
            return EstateId;
        }

        public void setEstateId(Object EstateId) {
            this.EstateId = EstateId;
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

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public int getUserDesingerID() {
            return UserDesingerID;
        }

        public void setUserDesingerID(int UserDesingerID) {
            this.UserDesingerID = UserDesingerID;
        }

        public int getUserSellerID() {
            return UserSellerID;
        }

        public void setUserSellerID(int UserSellerID) {
            this.UserSellerID = UserSellerID;
        }

        public int getUserWorkerID() {
            return UserWorkerID;
        }

        public void setUserWorkerID(int UserWorkerID) {
            this.UserWorkerID = UserWorkerID;
        }

        public int getHouseId() {
            return HouseId;
        }

        public void setHouseId(int HouseId) {
            this.HouseId = HouseId;
        }
    }
}
