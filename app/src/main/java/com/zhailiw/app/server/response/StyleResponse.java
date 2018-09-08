package com.zhailiw.app.server.response;

import java.util.List;

public class StyleResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"ClassifyID":240,"Name":"后现代风格","Pic":"/Images/ZL_Classify/2018/04/24/2018042415485699.jpg"},{"ClassifyID":239,"Name":"东南亚风格","Pic":"/Images/ZL_Classify/2018/04/24/2018042415491371.jpg"},{"ClassifyID":238,"Name":"小美风格","Pic":null},{"ClassifyID":237,"Name":"地中海风格","Pic":null},{"ClassifyID":236,"Name":"韩式风格","Pic":null},{"ClassifyID":235,"Name":"欧美风格","Pic":null},{"ClassifyID":234,"Name":"简欧风格","Pic":null},{"ClassifyID":233,"Name":"北欧风格","Pic":null},{"ClassifyID":232,"Name":"新中式风格","Pic":null},{"ClassifyID":231,"Name":"中式风格","Pic":null},{"ClassifyID":230,"Name":"现代风格","Pic":null}]
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
         * ClassifyID : 240
         * Name : 后现代风格
         * Pic : /Images/ZL_Classify/2018/04/24/2018042415485699.jpg
         */

        private int ClassifyID;
        private String Name;
        private String Pic;

        public int getClassifyID() {
            return ClassifyID;
        }

        public void setClassifyID(int ClassifyID) {
            this.ClassifyID = ClassifyID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }
    }
}
