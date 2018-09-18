package com.zhailiw.app.server.response;


import java.util.List;

public class ProgressListResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"ProgressID":13,"Name":"付款提示","CreateDate":"/Date(1535949376817)/","StartDate":"/Date(1539100800000)/","EndDate":"/Date(1541865600000)/","ProcessID":4,"Order":2,"State":null,"StateName":null,"DoneDate":null},{"ProgressID":12,"Name":"楼梯","CreateDate":"/Date(1535884593763)/","StartDate":"/Date(1519833600000)/","EndDate":"/Date(1541001600000)/","ProcessID":4,"Order":3,"State":null,"StateName":null,"DoneDate":null},{"ProgressID":9,"Name":"木","CreateDate":"/Date(1535609707573)/","StartDate":"/Date(1483200000000)/","EndDate":"/Date(1522512000000)/","ProcessID":4,"Order":4,"State":null,"StateName":null,"DoneDate":null},{"ProgressID":11,"Name":"水","CreateDate":"/Date(1535780209237)/","StartDate":"/Date(1517414400000)/","EndDate":"/Date(1525104000000)/","ProcessID":4,"Order":5,"State":329,"StateName":"已完成","DoneDate":null},{"ProgressID":5,"Name":"泥","CreateDate":"/Date(1535609051670)/","StartDate":"/Date(1514736000000)/","EndDate":"/Date(1519833600000)/","ProcessID":4,"Order":6,"State":null,"StateName":null,"DoneDate":null}]
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
         * ProgressID : 13
         * Name : 付款提示
         * CreateDate : /Date(1535949376817)/
         * StartDate : /Date(1539100800000)/
         * EndDate : /Date(1541865600000)/
         * ProcessID : 4
         * Order : 2
         * State : null
         * StateName : null
         * DoneDate : null
         */

        private int ProgressID;
        private String Name;
        private String CreateDate;
        private String StartDate;
        private String EndDate;
        private int ProcessID;
        private int Order;
        private int State;
        private String StateName;
        private String DoneDate;

        public int getProgressID() {
            return ProgressID;
        }

        public void setProgressID(int ProgressID) {
            this.ProgressID = ProgressID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public int getProcessID() {
            return ProcessID;
        }

        public void setProcessID(int ProcessID) {
            this.ProcessID = ProcessID;
        }

        public int getOrder() {
            return Order;
        }

        public void setOrder(int Order) {
            this.Order = Order;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String StateName) {
            this.StateName = StateName;
        }

        public String getDoneDate() {
            return DoneDate;
        }

        public void setDoneDate(String DoneDate) {
            this.DoneDate = DoneDate;
        }
    }
}
