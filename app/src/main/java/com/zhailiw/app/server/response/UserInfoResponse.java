package com.zhailiw.app.server.response;


public class UserInfoResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 0
     * data : {"UserID":1477,"UserName":"13729213017         ","Password":"123","CreateDate":"/Date(1524909200760)/","RealName":null,"NickName":"555","UserInfoID":1477,"RoleName":"普通用户","RoleID":3,"CellPhone":"13729213017         ","RongCloudToken":"c67fd49a23fd49dabad7df03e47d6a6e","LevelID":1,"LevelName":"V1","LevelAmount":500000,"LevelDiscount":0.8,"LevelRebate":0.1,"PhotoBig":"/Images/User/2018/05/16/2018051610120287_b.jpg","PhotoSmall":"/Images/User/2018/05/16/2018051610120287_s.jpg","Birthday":null,"SexName":"女","Sex":null}
     */

    private int state;
    private String msg;
    private int totalPages;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * UserID : 1477
         * UserName : 13729213017
         * Password : 123
         * CreateDate : /Date(1524909200760)/
         * RealName : null
         * NickName : 555
         * UserInfoID : 1477
         * RoleName : 普通用户
         * RoleID : 3
         * CellPhone : 13729213017
         * RongCloudToken : c67fd49a23fd49dabad7df03e47d6a6e
         * LevelID : 1
         * LevelName : V1
         * LevelAmount : 500000
         * LevelDiscount : 0.8
         * LevelRebate : 0.1
         * PhotoBig : /Images/User/2018/05/16/2018051610120287_b.jpg
         * PhotoSmall : /Images/User/2018/05/16/2018051610120287_s.jpg
         * Birthday : null
         * SexName : 女
         * Sex : null
         */

        private int UserID;
        private String UserName;
        private String Password;
        private String CreateDate;
        private Object RealName;
        private String NickName;
        private int UserInfoID;
        private String RoleName;
        private int RoleID;
        private String CellPhone;
        private String RongCloudToken;
        private int LevelID;
        private String LevelName;
        private int LevelAmount;
        private double LevelDiscount;
        private double LevelRebate;
        private String PhotoBig;
        private String PhotoSmall;
        private String Birthday;
        private String SexName;
        private Object Sex;

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getRealName() {
            return RealName;
        }

        public void setRealName(Object RealName) {
            this.RealName = RealName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public int getUserInfoID() {
            return UserInfoID;
        }

        public void setUserInfoID(int UserInfoID) {
            this.UserInfoID = UserInfoID;
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

        public String getCellPhone() {
            return CellPhone;
        }

        public void setCellPhone(String CellPhone) {
            this.CellPhone = CellPhone;
        }

        public String getRongCloudToken() {
            return RongCloudToken;
        }

        public void setRongCloudToken(String RongCloudToken) {
            this.RongCloudToken = RongCloudToken;
        }

        public int getLevelID() {
            return LevelID;
        }

        public void setLevelID(int LevelID) {
            this.LevelID = LevelID;
        }

        public String getLevelName() {
            return LevelName;
        }

        public void setLevelName(String LevelName) {
            this.LevelName = LevelName;
        }

        public int getLevelAmount() {
            return LevelAmount;
        }

        public void setLevelAmount(int LevelAmount) {
            this.LevelAmount = LevelAmount;
        }

        public double getLevelDiscount() {
            return LevelDiscount;
        }

        public void setLevelDiscount(double LevelDiscount) {
            this.LevelDiscount = LevelDiscount;
        }

        public double getLevelRebate() {
            return LevelRebate;
        }

        public void setLevelRebate(double LevelRebate) {
            this.LevelRebate = LevelRebate;
        }

        public String getPhotoBig() {
            return PhotoBig;
        }

        public void setPhotoBig(String PhotoBig) {
            this.PhotoBig = PhotoBig;
        }

        public String getPhotoSmall() {
            return PhotoSmall;
        }

        public void setPhotoSmall(String PhotoSmall) {
            this.PhotoSmall = PhotoSmall;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public String getSexName() {
            return SexName;
        }

        public void setSexName(String SexName) {
            this.SexName = SexName;
        }

        public Object getSex() {
            return Sex;
        }

        public void setSex(Object Sex) {
            this.Sex = Sex;
        }
    }
}
