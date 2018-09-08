package com.zhailiw.app.server.response;

import java.util.List;

public class ADResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"AdvertisementID":31,"ADName":"地产图","ADPhoto":"/Images/ZL_Gallery/2018/05/10/2018051014223524_b.JPG","Url":null,"Type":null,"OnOff":true,"CreateDate":"/Date(1523672326237)/"}]
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
         * AdvertisementID : 31
         * ADName : 地产图
         * ADPhoto : /Images/ZL_Gallery/2018/05/10/2018051014223524_b.JPG
         * Url : null
         * Type : null
         * OnOff : true
         * CreateDate : /Date(1523672326237)/
         */

        private int AdvertisementID;
        private String ADName;
        private String ADPhoto;
        private Object Url;
        private Object Type;
        private boolean OnOff;
        private String CreateDate;

        public int getAdvertisementID() {
            return AdvertisementID;
        }

        public void setAdvertisementID(int AdvertisementID) {
            this.AdvertisementID = AdvertisementID;
        }

        public String getADName() {
            return ADName;
        }

        public void setADName(String ADName) {
            this.ADName = ADName;
        }

        public String getADPhoto() {
            return ADPhoto;
        }

        public void setADPhoto(String ADPhoto) {
            this.ADPhoto = ADPhoto;
        }

        public Object getUrl() {
            return Url;
        }

        public void setUrl(Object Url) {
            this.Url = Url;
        }

        public Object getType() {
            return Type;
        }

        public void setType(Object Type) {
            this.Type = Type;
        }

        public boolean isOnOff() {
            return OnOff;
        }

        public void setOnOff(boolean OnOff) {
            this.OnOff = OnOff;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
