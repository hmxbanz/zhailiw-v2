package com.zhailiw.app.server.response;

import java.util.List;

public class GalleryResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 1
     * data : [{"GalleryID":1,"GalleryName":"图库一","GalleryCover":null,"CreateDate":"/Date(1524108393420)/"},{"GalleryID":2,"GalleryName":"dd","GalleryCover":null,"CreateDate":"/Date(1524108440337)/"},{"GalleryID":6,"GalleryName":"00022","GalleryCover":"/Images/ZL_Gallery/2018/04/20/2018042010160328_s.jpg","CreateDate":"/Date(1524125341347)/"},{"GalleryID":7,"GalleryName":"ddd","GalleryCover":"/Images/ZL_Gallery/2018/05/07/2018050711493122_s.png","CreateDate":"/Date(1525664947963)/"}]
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
         * GalleryID : 1
         * GalleryName : 图库一
         * GalleryCover : null
         * CreateDate : /Date(1524108393420)/
         */

        private int GalleryID;
        private String GalleryName;
        private Object GalleryCover;
        private String CreateDate;

        public int getGalleryID() {
            return GalleryID;
        }

        public void setGalleryID(int GalleryID) {
            this.GalleryID = GalleryID;
        }

        public String getGalleryName() {
            return GalleryName;
        }

        public void setGalleryName(String GalleryName) {
            this.GalleryName = GalleryName;
        }

        public Object getGalleryCover() {
            return GalleryCover;
        }

        public void setGalleryCover(Object GalleryCover) {
            this.GalleryCover = GalleryCover;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
