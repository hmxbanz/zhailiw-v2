package com.zhailiw.app.server.response;


import java.util.List;

public class DecorateListResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"ProcessDetailID":1,"Name":"上传设计稿","Pic1":null,"ProcessID":1,"CreateDate":"/Date(1534521600000)/","Pic2":null,"Pic3":null,"Pic1_Big":null,"Pic2_Big":null,"Pic3_Big":null,"Remark":"第一次上传设计合同","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":3,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/28/2018082815351103_s.jpg","ProcessID":1,"CreateDate":"/Date(1535441710800)/","Pic2":"/Images/HouseDialog/2018/08/28/2018082815351093_s.jpg","Pic3":"System.Web.HttpPostedFileWrapper","Pic1_Big":"/Images/HouseDialog/2018/08/28/2018082815351103_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/28/2018082815351093_b.jpg","Pic3_Big":null,"Remark":"客户确认使用本方案","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":4,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/28/2018082815431708_s.jpg","ProcessID":1,"CreateDate":"/Date(1535442196883)/","Pic2":"/Images/HouseDialog/2018/08/28/2018082815431698_s.jpg","Pic3":"System.Web.HttpPostedFileWrapper","Pic1_Big":"/Images/HouseDialog/2018/08/28/2018082815431708_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/28/2018082815431698_b.jpg","Pic3_Big":null,"Remark":"客户已确认","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":5,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/28/2018082815494348_s.jpg","ProcessID":1,"CreateDate":"/Date(1535442583487)/","Pic2":"/Images/HouseDialog/2018/08/28/2018082815494354_s.jpg","Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/08/28/2018082815494348_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/28/2018082815494354_b.jpg","Pic3_Big":null,"Remark":"123","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":6,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/28/2018082815533262_s.jpg","ProcessID":1,"CreateDate":"/Date(1535442812430)/","Pic2":"/Images/HouseDialog/2018/08/28/2018082815533248_s.jpg","Pic3":"System.Web.HttpPostedFileWrapper","Pic1_Big":"/Images/HouseDialog/2018/08/28/2018082815533262_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/28/2018082815533248_b.jpg","Pic3_Big":null,"Remark":"123","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":7,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/28/2018082816055850_s.jpg","ProcessID":1,"CreateDate":"/Date(1535443558247)/","Pic2":"/Images/HouseDialog/2018/08/28/2018082816055839_s.jpg","Pic3":"System.Web.HttpPostedFileWrapper","Pic1_Big":"/Images/HouseDialog/2018/08/28/2018082816055850_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/28/2018082816055839_b.jpg","Pic3_Big":null,"Remark":"最新合同","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":9,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011014542_s.jpg","ProcessID":1,"CreateDate":"/Date(1535598105430)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011014542_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"内容2","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":10,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011055949_s.jpg","ProcessID":1,"CreateDate":"/Date(1535598359497)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011055949_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"内容","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":16,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011205630_s.jpg","ProcessID":1,"CreateDate":"/Date(1535599256310)/","Pic2":"/Images/HouseDialog/2018/08/30/2018083011205651_s.jpg","Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011205630_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/30/2018083011205651_b.jpg","Pic3_Big":null,"Remark":"内容","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":17,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011211490_s.jpg","ProcessID":1,"CreateDate":"/Date(1535599274903)/","Pic2":"/Images/HouseDialog/2018/08/30/2018083011211511_s.jpg","Pic3":"/Images/HouseDialog/2018/08/30/2018083011211532_s.jpg","Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011211490_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/30/2018083011211511_b.jpg","Pic3_Big":"/Images/HouseDialog/2018/08/30/2018083011211532_b.jpg","Remark":"内容","DetailType":334,"DetailTypeName":"进材日志","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":19,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011275658_s.jpg","ProcessID":1,"CreateDate":"/Date(1535599676590)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011275658_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"内容","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":20,"Name":null,"Pic1":"/Images/HouseDialog/2018/08/30/2018083011532614_s.jpg","ProcessID":1,"CreateDate":"/Date(1535601206147)/","Pic2":"/Images/HouseDialog/2018/08/30/2018083011532620_s.jpg","Pic3":"/Images/HouseDialog/2018/08/30/2018083011532625_s.jpg","Pic1_Big":"/Images/HouseDialog/2018/08/30/2018083011532614_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/08/30/2018083011532620_b.jpg","Pic3_Big":"/Images/HouseDialog/2018/08/30/2018083011532625_b.jpg","Remark":"看看有没有三张图","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":21,"Name":null,"Pic1":null,"ProcessID":1,"CreateDate":"/Date(1535601740423)/","Pic2":null,"Pic3":null,"Pic1_Big":null,"Pic2_Big":null,"Pic3_Big":null,"Remark":"内容","DetailType":331,"DetailTypeName":"设计稿","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":25,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/01/2018090117131235_s.jpg","ProcessID":1,"CreateDate":"/Date(1535793192357)/","Pic2":"/Images/HouseDialog/2018/09/01/2018090117131263_s.jpg","Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/09/01/2018090117131235_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/09/01/2018090117131263_b.jpg","Pic3_Big":null,"Remark":"备注","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":31,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/03/2018090311412725_s.jpg","ProcessID":1,"CreateDate":"/Date(1535946087253)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/09/03/2018090311412725_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"第二稿","DetailType":331,"DetailTypeName":"设计稿","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":32,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/03/2018090313102346_s.jpg","ProcessID":1,"CreateDate":"/Date(1535951423460)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/09/03/2018090313102346_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"666","DetailType":331,"DetailTypeName":"设计稿","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":33,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/03/2018090313110916_s.jpg","ProcessID":1,"CreateDate":"/Date(1535951469160)/","Pic2":null,"Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/09/03/2018090313110916_b.jpg","Pic2_Big":null,"Pic3_Big":null,"Remark":"合同已出","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":null},{"ProcessDetailID":37,"Name":null,"Pic1":null,"ProcessID":1,"CreateDate":"/Date(1536043787240)/","Pic2":null,"Pic3":null,"Pic1_Big":null,"Pic2_Big":null,"Pic3_Big":null,"Remark":"9张合同","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":2},{"ProcessDetailID":38,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/04/2018090415054947_s.jpg","ProcessID":1,"CreateDate":"/Date(1536044749473)/","Pic2":"/Images/HouseDialog/2018/09/04/2018090415054980_s.jpg","Pic3":"/Images/HouseDialog/2018/09/04/2018090415054984_s.jpg","Pic1_Big":"/Images/HouseDialog/2018/09/04/2018090415054947_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/09/04/2018090415054980_b.jpg","Pic3_Big":"/Images/HouseDialog/2018/09/04/2018090415054984_b.jpg","Remark":"9张图","DetailType":331,"DetailTypeName":"设计稿","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":2},{"ProcessDetailID":39,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/04/2018090415085645_s.jpg","ProcessID":1,"CreateDate":"/Date(1536044936453)/","Pic2":"/Images/HouseDialog/2018/09/04/2018090415085675_s.jpg","Pic3":"/Images/HouseDialog/2018/09/04/2018090415085696_s.jpg","Pic1_Big":"/Images/HouseDialog/2018/09/04/2018090415085645_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/09/04/2018090415085675_b.jpg","Pic3_Big":"/Images/HouseDialog/2018/09/04/2018090415085696_b.jpg","Remark":"行不行","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":2},{"ProcessDetailID":40,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/04/2018090415110924_s.jpg","ProcessID":1,"CreateDate":"/Date(1536045069243)/","Pic2":"/Images/HouseDialog/2018/09/04/2018090415110955_s.jpg","Pic3":"/Images/HouseDialog/2018/09/04/2018090415110984_s.jpg","Pic1_Big":"/Images/HouseDialog/2018/09/04/2018090415110924_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/09/04/2018090415110955_b.jpg","Pic3_Big":"/Images/HouseDialog/2018/09/04/2018090415110984_b.jpg","Remark":"再来","DetailType":332,"DetailTypeName":"设计合同","ProgressID":null,"Pic9_Big":"/Images/HouseDialog/2018/09/04/2018090415111088_b.jpg","Pic8_Big":"/Images/HouseDialog/2018/09/04/2018090415111083_b.jpg","Pic7_Big":"/Images/HouseDialog/2018/09/04/2018090415111061_b.jpg","Pic6_Big":"/Images/HouseDialog/2018/09/04/2018090415111040_b.jpg","Pic5_Big":"/Images/HouseDialog/2018/09/04/2018090415111010_b.jpg","Pic4_Big":"/Images/HouseDialog/2018/09/04/2018090415111006_b.jpg","Pic9":"/Images/HouseDialog/2018/09/04/2018090415111088_s.jpg","Pic8":"/Images/HouseDialog/2018/09/04/2018090415111083_s.jpg","Pic7":"/Images/HouseDialog/2018/09/04/2018090415111061_s.jpg","Pic6":"/Images/HouseDialog/2018/09/04/2018090415111040_s.jpg","Pic5":"/Images/HouseDialog/2018/09/04/2018090415111010_s.jpg","Pic4":"/Images/HouseDialog/2018/09/04/2018090415111006_s.jpg","IsTop":2},{"ProcessDetailID":41,"Name":null,"Pic1":"/Images/HouseDialog/2018/09/05/2018090500255494_s.jpg","ProcessID":1,"CreateDate":"/Date(1536078354943)/","Pic2":"/Images/HouseDialog/2018/09/05/2018090500255526_s.jpg","Pic3":null,"Pic1_Big":"/Images/HouseDialog/2018/09/05/2018090500255494_b.jpg","Pic2_Big":"/Images/HouseDialog/2018/09/05/2018090500255526_b.jpg","Pic3_Big":null,"Remark":"设计稿第二版","DetailType":331,"DetailTypeName":"设计稿","ProgressID":null,"Pic9_Big":null,"Pic8_Big":null,"Pic7_Big":null,"Pic6_Big":null,"Pic5_Big":null,"Pic4_Big":null,"Pic9":null,"Pic8":null,"Pic7":null,"Pic6":null,"Pic5":null,"Pic4":null,"IsTop":2}]
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
         * ProcessDetailID : 1
         * Name : 上传设计稿
         * Pic1 : null
         * ProcessID : 1
         * CreateDate : /Date(1534521600000)/
         * Pic2 : null
         * Pic3 : null
         * Pic1_Big : null
         * Pic2_Big : null
         * Pic3_Big : null
         * Remark : 第一次上传设计合同
         * DetailType : 332
         * DetailTypeName : 设计合同
         * ProgressID : null
         * Pic9_Big : null
         * Pic8_Big : null
         * Pic7_Big : null
         * Pic6_Big : null
         * Pic5_Big : null
         * Pic4_Big : null
         * Pic9 : null
         * Pic8 : null
         * Pic7 : null
         * Pic6 : null
         * Pic5 : null
         * Pic4 : null
         * IsTop : null
         */

        private int ProcessDetailID;
        private String Name;
        private String Pic1;
        private int ProcessID;
        private String CreateDate;
        private String Pic2;
        private String Pic3;
        private String Pic1_Big;
        private String Pic2_Big;
        private String Pic3_Big;
        private String Remark;
        private int DetailType;
        private String DetailTypeName;
        private int ProgressID;
        private String Pic9_Big;
        private String Pic8_Big;
        private String Pic7_Big;
        private String Pic6_Big;
        private String Pic5_Big;
        private String Pic4_Big;
        private String Pic9;
        private String Pic8;
        private String Pic7;
        private String Pic6;
        private String Pic5;
        private String Pic4;
        private int IsTop;

        public int getProcessDetailID() {
            return ProcessDetailID;
        }

        public void setProcessDetailID(int ProcessDetailID) {
            this.ProcessDetailID = ProcessDetailID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPic1() {
            return Pic1;
        }

        public void setPic1(String Pic1) {
            this.Pic1 = Pic1;
        }

        public int getProcessID() {
            return ProcessID;
        }

        public void setProcessID(int ProcessID) {
            this.ProcessID = ProcessID;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getPic2() {
            return Pic2;
        }

        public void setPic2(String Pic2) {
            this.Pic2 = Pic2;
        }

        public String getPic3() {
            return Pic3;
        }

        public void setPic3(String Pic3) {
            this.Pic3 = Pic3;
        }

        public String getPic1_Big() {
            return Pic1_Big;
        }

        public void setPic1_Big(String Pic1_Big) {
            this.Pic1_Big = Pic1_Big;
        }

        public String getPic2_Big() {
            return Pic2_Big;
        }

        public void setPic2_Big(String Pic2_Big) {
            this.Pic2_Big = Pic2_Big;
        }

        public String getPic3_Big() {
            return Pic3_Big;
        }

        public void setPic3_Big(String Pic3_Big) {
            this.Pic3_Big = Pic3_Big;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getDetailType() {
            return DetailType;
        }

        public void setDetailType(int DetailType) {
            this.DetailType = DetailType;
        }

        public String getDetailTypeName() {
            return DetailTypeName;
        }

        public void setDetailTypeName(String DetailTypeName) {
            this.DetailTypeName = DetailTypeName;
        }

        public int getProgressID() {
            return ProgressID;
        }

        public void setProgressID(int ProgressID) {
            this.ProgressID = ProgressID;
        }

        public String getPic9_Big() {
            return Pic9_Big;
        }

        public void setPic9_Big(String Pic9_Big) {
            this.Pic9_Big = Pic9_Big;
        }

        public String getPic8_Big() {
            return Pic8_Big;
        }

        public void setPic8_Big(String Pic8_Big) {
            this.Pic8_Big = Pic8_Big;
        }

        public String getPic7_Big() {
            return Pic7_Big;
        }

        public void setPic7_Big(String Pic7_Big) {
            this.Pic7_Big = Pic7_Big;
        }

        public String getPic6_Big() {
            return Pic6_Big;
        }

        public void setPic6_Big(String Pic6_Big) {
            this.Pic6_Big = Pic6_Big;
        }

        public String getPic5_Big() {
            return Pic5_Big;
        }

        public void setPic5_Big(String Pic5_Big) {
            this.Pic5_Big = Pic5_Big;
        }

        public String getPic4_Big() {
            return Pic4_Big;
        }

        public void setPic4_Big(String Pic4_Big) {
            this.Pic4_Big = Pic4_Big;
        }

        public String getPic9() {
            return Pic9;
        }

        public void setPic9(String Pic9) {
            this.Pic9 = Pic9;
        }

        public String getPic8() {
            return Pic8;
        }

        public void setPic8(String Pic8) {
            this.Pic8 = Pic8;
        }

        public String getPic7() {
            return Pic7;
        }

        public void setPic7(String Pic7) {
            this.Pic7 = Pic7;
        }

        public String getPic6() {
            return Pic6;
        }

        public void setPic6(String Pic6) {
            this.Pic6 = Pic6;
        }

        public String getPic5() {
            return Pic5;
        }

        public void setPic5(String Pic5) {
            this.Pic5 = Pic5;
        }

        public String getPic4() {
            return Pic4;
        }

        public void setPic4(String Pic4) {
            this.Pic4 = Pic4;
        }

        public int getIsTop() {
            return IsTop;
        }

        public void setIsTop(int IsTop) {
            this.IsTop = IsTop;
        }
    }
}
