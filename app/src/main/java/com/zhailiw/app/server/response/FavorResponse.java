package com.zhailiw.app.server.response;

import java.util.List;

public class FavorResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 1
     * data : [{"FavorID":7,"UserInfoID":1475,"ProductId":35,"ProductNo":"ZL10-F-35","ProductName":"沙发","ProductInfo":"豪华版","ProductTypeName":"沙发","FactoryProductNo":null,"CreateDate":"/Date(1526693525817)/","MatchTypeName":"卧室","MaterialTypeName":"板材类","ProductImage":"/Images/ZL_Product/2018/04/16/2018041610403149_s.jpg","ProductPrice":143}]
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
         * FavorID : 7
         * UserInfoID : 1475
         * ProductId : 35
         * ProductNo : ZL10-F-35
         * ProductName : 沙发
         * ProductInfo : 豪华版
         * ProductTypeName : 沙发
         * FactoryProductNo : null
         * CreateDate : /Date(1526693525817)/
         * MatchTypeName : 卧室
         * MaterialTypeName : 板材类
         * ProductImage : /Images/ZL_Product/2018/04/16/2018041610403149_s.jpg
         * ProductPrice : 143
         */

        private int FavorID;
        private int UserInfoID;
        private int ProductId;
        private String ProductNo;
        private String ProductName;
        private String ProductInfo;
        private String ProductTypeName;
        private Object FactoryProductNo;
        private String CreateDate;
        private String MatchTypeName;
        private String MaterialTypeName;
        private String ProductImage;
        private int ProductPrice;

        public int getFavorID() {
            return FavorID;
        }

        public void setFavorID(int FavorID) {
            this.FavorID = FavorID;
        }

        public int getUserInfoID() {
            return UserInfoID;
        }

        public void setUserInfoID(int UserInfoID) {
            this.UserInfoID = UserInfoID;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getProductNo() {
            return ProductNo;
        }

        public void setProductNo(String ProductNo) {
            this.ProductNo = ProductNo;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getProductInfo() {
            return ProductInfo;
        }

        public void setProductInfo(String ProductInfo) {
            this.ProductInfo = ProductInfo;
        }

        public String getProductTypeName() {
            return ProductTypeName;
        }

        public void setProductTypeName(String ProductTypeName) {
            this.ProductTypeName = ProductTypeName;
        }

        public Object getFactoryProductNo() {
            return FactoryProductNo;
        }

        public void setFactoryProductNo(Object FactoryProductNo) {
            this.FactoryProductNo = FactoryProductNo;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getMatchTypeName() {
            return MatchTypeName;
        }

        public void setMatchTypeName(String MatchTypeName) {
            this.MatchTypeName = MatchTypeName;
        }

        public String getMaterialTypeName() {
            return MaterialTypeName;
        }

        public void setMaterialTypeName(String MaterialTypeName) {
            this.MaterialTypeName = MaterialTypeName;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
        }

        public int getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(int ProductPrice) {
            this.ProductPrice = ProductPrice;
        }
    }
}
