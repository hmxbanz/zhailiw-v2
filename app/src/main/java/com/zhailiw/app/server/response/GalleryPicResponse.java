package com.zhailiw.app.server.response;

import java.util.List;

public class GalleryPicResponse {

    /**
     * state : 1
     * msg : 成功
     * data : [{"PhotoID":223,"GalleryID":7,"PhotoBig":"/Images/ZL_Gallery/2018/05/08/2018050813472842_b.jpg","PhotoSmall":"/Images/ZL_Gallery/2018/05/08/2018050813472842_s.jpg","PhotoType":276},{"PhotoID":224,"GalleryID":7,"PhotoBig":"/Images/ZL_Gallery/2018/05/08/2018050813473758_b.png","PhotoSmall":"/Images/ZL_Gallery/2018/05/08/2018050813473758_s.png","PhotoType":276}]
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
         * PhotoID : 223
         * GalleryID : 7
         * PhotoBig : /Images/ZL_Gallery/2018/05/08/2018050813472842_b.jpg
         * PhotoSmall : /Images/ZL_Gallery/2018/05/08/2018050813472842_s.jpg
         * PhotoType : 276
         */

        private int PhotoID;
        private int GalleryID;
        private String PhotoBig;
        private String PhotoSmall;
        private int PhotoType;

        public int getPhotoID() {
            return PhotoID;
        }

        public void setPhotoID(int PhotoID) {
            this.PhotoID = PhotoID;
        }

        public int getGalleryID() {
            return GalleryID;
        }

        public void setGalleryID(int GalleryID) {
            this.GalleryID = GalleryID;
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

        public int getPhotoType() {
            return PhotoType;
        }

        public void setPhotoType(int PhotoType) {
            this.PhotoType = PhotoType;
        }
    }
}
