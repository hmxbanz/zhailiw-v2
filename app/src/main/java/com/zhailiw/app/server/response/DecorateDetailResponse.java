package com.zhailiw.app.server.response;


import java.util.List;

public class DecorateDetailResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"DecorateID":7,"HouseID":16,"Name":"施工进程","ProcessID":1,"ProcessName":"装修设计","CreateDate":"/Date(1534521600000)/","ProcessState":318,"ProcessOrder":null,"ProcessRemark":null,"ProcessStateName":"未完成","HouseName":"四合院","HouseSize":"128","HouseBudget":125000,"HouseAddress":"广东省汕头市澄海区广益街道","DesingerRealName":null,"DesingerCellPhone":"13729213015         ","SellerRealName":"林志炫","SellerCellPhone":"15217614631","WorkerRealName":null,"WorkerCellPhone":"15816760707"},{"DecorateID":7,"HouseID":16,"Name":"施工进程","ProcessID":2,"ProcessName":"装修预算","CreateDate":"/Date(1534521600000)/","ProcessState":318,"ProcessOrder":null,"ProcessRemark":null,"ProcessStateName":"未完成","HouseName":"四合院","HouseSize":"128","HouseBudget":125000,"HouseAddress":"广东省汕头市澄海区广益街道","DesingerRealName":null,"DesingerCellPhone":"13729213015         ","SellerRealName":"林志炫","SellerCellPhone":"15217614631","WorkerRealName":null,"WorkerCellPhone":"15816760707"},{"DecorateID":7,"HouseID":16,"Name":"施工进程","ProcessID":3,"ProcessName":"装修合同","CreateDate":"/Date(1534521600000)/","ProcessState":318,"ProcessOrder":null,"ProcessRemark":null,"ProcessStateName":"未完成","HouseName":"四合院","HouseSize":"128","HouseBudget":125000,"HouseAddress":"广东省汕头市澄海区广益街道","DesingerRealName":null,"DesingerCellPhone":"13729213015         ","SellerRealName":"林志炫","SellerCellPhone":"15217614631","WorkerRealName":null,"WorkerCellPhone":"15816760707"},{"DecorateID":7,"HouseID":16,"Name":"施工进程","ProcessID":4,"ProcessName":"施工进度","CreateDate":"/Date(1534521600000)/","ProcessState":318,"ProcessOrder":null,"ProcessRemark":null,"ProcessStateName":"未完成","HouseName":"四合院","HouseSize":"128","HouseBudget":125000,"HouseAddress":"广东省汕头市澄海区广益街道","DesingerRealName":null,"DesingerCellPhone":"13729213015         ","SellerRealName":"林志炫","SellerCellPhone":"15217614631","WorkerRealName":null,"WorkerCellPhone":"15816760707"},{"DecorateID":7,"HouseID":16,"Name":"施工进程","ProcessID":5,"ProcessName":"装修验收","CreateDate":"/Date(1534521600000)/","ProcessState":318,"ProcessOrder":null,"ProcessRemark":null,"ProcessStateName":"未完成","HouseName":"四合院","HouseSize":"128","HouseBudget":125000,"HouseAddress":"广东省汕头市澄海区广益街道","DesingerRealName":null,"DesingerCellPhone":"13729213015         ","SellerRealName":"林志炫","SellerCellPhone":"15217614631","WorkerRealName":null,"WorkerCellPhone":"15816760707"}]
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
         * DecorateID : 7
         * HouseID : 16
         * Name : 施工进程
         * ProcessID : 1
         * ProcessName : 装修设计
         * CreateDate : /Date(1534521600000)/
         * ProcessState : 318
         * ProcessOrder : null
         * ProcessRemark : null
         * ProcessStateName : 未完成
         * HouseName : 四合院
         * HouseSize : 128
         * HouseBudget : 125000
         * HouseAddress : 广东省汕头市澄海区广益街道
         * DesingerRealName : null
         * DesingerCellPhone : 13729213015
         * SellerRealName : 林志炫
         * SellerCellPhone : 15217614631
         * WorkerRealName : null
         * WorkerCellPhone : 15816760707
         */

        private int DecorateID;
        private int HouseID;
        private String Name;
        private int ProcessID;
        private String ProcessName;
        private String CreateDate;
        private int ProcessState;
        private Object ProcessOrder;
        private Object ProcessRemark;
        private String ProcessStateName;
        private String HouseName;
        private String HouseSize;
        private int HouseBudget;
        private String HouseAddress;
        private String DesingerRealName;
        private String DesingerCellPhone;
        private String SellerRealName;
        private String SellerCellPhone;
        private String WorkerRealName;
        private String WorkerCellPhone;

        public int getDecorateID() {
            return DecorateID;
        }

        public void setDecorateID(int DecorateID) {
            this.DecorateID = DecorateID;
        }

        public int getHouseID() {
            return HouseID;
        }

        public void setHouseID(int HouseID) {
            this.HouseID = HouseID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getProcessID() {
            return ProcessID;
        }

        public void setProcessID(int ProcessID) {
            this.ProcessID = ProcessID;
        }

        public String getProcessName() {
            return ProcessName;
        }

        public void setProcessName(String ProcessName) {
            this.ProcessName = ProcessName;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public int getProcessState() {
            return ProcessState;
        }

        public void setProcessState(int ProcessState) {
            this.ProcessState = ProcessState;
        }

        public Object getProcessOrder() {
            return ProcessOrder;
        }

        public void setProcessOrder(Object ProcessOrder) {
            this.ProcessOrder = ProcessOrder;
        }

        public Object getProcessRemark() {
            return ProcessRemark;
        }

        public void setProcessRemark(Object ProcessRemark) {
            this.ProcessRemark = ProcessRemark;
        }

        public String getProcessStateName() {
            return ProcessStateName;
        }

        public void setProcessStateName(String ProcessStateName) {
            this.ProcessStateName = ProcessStateName;
        }

        public String getHouseName() {
            return HouseName;
        }

        public void setHouseName(String HouseName) {
            this.HouseName = HouseName;
        }

        public String getHouseSize() {
            return HouseSize;
        }

        public void setHouseSize(String HouseSize) {
            this.HouseSize = HouseSize;
        }

        public int getHouseBudget() {
            return HouseBudget;
        }

        public void setHouseBudget(int HouseBudget) {
            this.HouseBudget = HouseBudget;
        }

        public String getHouseAddress() {
            return HouseAddress;
        }

        public void setHouseAddress(String HouseAddress) {
            this.HouseAddress = HouseAddress;
        }

        public String getDesingerRealName() {
            return DesingerRealName;
        }

        public void setDesingerRealName(String DesingerRealName) {
            this.DesingerRealName = DesingerRealName;
        }

        public String getDesingerCellPhone() {
            return DesingerCellPhone;
        }

        public void setDesingerCellPhone(String DesingerCellPhone) {
            this.DesingerCellPhone = DesingerCellPhone;
        }

        public String getSellerRealName() {
            return SellerRealName;
        }

        public void setSellerRealName(String SellerRealName) {
            this.SellerRealName = SellerRealName;
        }

        public String getSellerCellPhone() {
            return SellerCellPhone;
        }

        public void setSellerCellPhone(String SellerCellPhone) {
            this.SellerCellPhone = SellerCellPhone;
        }

        public String getWorkerRealName() {
            return WorkerRealName;
        }

        public void setWorkerRealName(String WorkerRealName) {
            this.WorkerRealName = WorkerRealName;
        }

        public String getWorkerCellPhone() {
            return WorkerCellPhone;
        }

        public void setWorkerCellPhone(String WorkerCellPhone) {
            this.WorkerCellPhone = WorkerCellPhone;
        }
    }
}
