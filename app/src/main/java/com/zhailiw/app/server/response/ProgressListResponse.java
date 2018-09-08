package com.zhailiw.app.server.response;


import java.util.List;

public class ProgressListResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"ProgressID":1,"Name":"泥","CreateDate":"/Date(1534521600000)/","StartDate":"/Date(1535731200000)/","EndDate":"/Date(1537372800000)/","ProcessID":4,"Order":null},{"ProgressID":2,"Name":"木","CreateDate":"/Date(1534521600000)/","StartDate":"/Date(1536163200000)/","EndDate":"/Date(1537372800000)/","ProcessID":4,"Order":null},{"ProgressID":3,"Name":"漆","CreateDate":"/Date(1534521600000)/","StartDate":"/Date(1536336000000)/","EndDate":"/Date(1537718400000)/","ProcessID":4,"Order":null}]
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
         * ProgressID : 1
         * Name : 泥
         * CreateDate : /Date(1534521600000)/
         * StartDate : /Date(1535731200000)/
         * EndDate : /Date(1537372800000)/
         * ProcessID : 4
         * Order : null
         */

        private int ProgressID;
        private String Name;
        private String CreateDate;
        private String StartDate;
        private String EndDate;
        private int ProcessID;
        private int Order;

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
    }
}
