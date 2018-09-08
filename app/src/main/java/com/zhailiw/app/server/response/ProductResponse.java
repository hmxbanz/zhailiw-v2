package com.zhailiw.app.server.response;

import java.util.List;

public class ProductResponse {

    /**
     * state : 1
     * msg : 成功
     * data : {"ADPhotoes":[{"PhotoType":269,"PhotoSmall":"/Images/ZL_Product/2018/05/10/2018051017304037_s.png","PhotoBig":"/Images/ZL_Product/2018/05/10/2018051017304037_b.png"}],"DetailPhotoes":[{"PhotoType":270,"PhotoSmall":"/Images/ZL_Product/2018/05/19/2018051911323308_s.png","PhotoBig":"/Images/ZL_Product/2018/05/19/2018051911323308_b.png"}],"CompanyId":1,"CompanyName":"劲凯客厅家具","CompanyAddr":"广东省","Tel":"8888888","CellPhone":"13713713713","ProductId":49,"ProductNo":"J001-F-49","ProductName":"第十个","ProductInfo":"第十个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"陈先生","CompanyCode":"J001","CreateDate":"/Date(1525944640367)/","ProductTypeId":248,"MatchTypeId":244,"MatchTypeName":"卧室","MaterialTypeId":262,"MaterialTypeName":"金属架类","FactoryProductNo":"第十个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017304037_s.png","ProductStyleId":235}
     */

    private int state;
    private String msg;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ADPhotoes : [{"PhotoType":269,"PhotoSmall":"/Images/ZL_Product/2018/05/10/2018051017304037_s.png","PhotoBig":"/Images/ZL_Product/2018/05/10/2018051017304037_b.png"}]
         * DetailPhotoes : [{"PhotoType":270,"PhotoSmall":"/Images/ZL_Product/2018/05/19/2018051911323308_s.png","PhotoBig":"/Images/ZL_Product/2018/05/19/2018051911323308_b.png"}]
         * CompanyId : 1
         * CompanyName : 劲凯客厅家具
         * CompanyAddr : 广东省
         * Tel : 8888888
         * CellPhone : 13713713713
         * ProductId : 49
         * ProductNo : J001-F-49
         * ProductName : 第十个
         * ProductInfo : 第十个
         * ProductTypeName : 沙发
         * ProductTypeCode : F
         * Contact : 陈先生
         * CompanyCode : J001
         * CreateDate : /Date(1525944640367)/
         * ProductTypeId : 248
         * MatchTypeId : 244
         * MatchTypeName : 卧室
         * MaterialTypeId : 262
         * MaterialTypeName : 金属架类
         * FactoryProductNo : 第十个
         * ProductPrice : null
         * ProductImage : /Images/ZL_Product/2018/05/10/2018051017304037_s.png
         * ProductStyleId : 235
         */

        private int CompanyId;
        private String CompanyName;
        private String CompanyAddr;
        private String Tel;
        private String CellPhone;
        private int ProductId;
        private String ProductNo;
        private String ProductName;
        private String ProductInfo;
        private String ProductTypeName;
        private String ProductTypeCode;
        private String Contact;
        private String CompanyCode;
        private String CreateDate;
        private int ProductTypeId;
        private int MatchTypeId;
        private String MatchTypeName;
        private int MaterialTypeId;
        private String MaterialTypeName;
        private String FactoryProductNo;
        private Object ProductPrice;
        private String ProductImage;
        private int ProductStyleId;
        private List<ADPhotoesBean> ADPhotoes;
        private List<DetailPhotoesBean> DetailPhotoes;

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getCompanyAddr() {
            return CompanyAddr;
        }

        public void setCompanyAddr(String CompanyAddr) {
            this.CompanyAddr = CompanyAddr;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getCellPhone() {
            return CellPhone;
        }

        public void setCellPhone(String CellPhone) {
            this.CellPhone = CellPhone;
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

        public String getProductTypeCode() {
            return ProductTypeCode;
        }

        public void setProductTypeCode(String ProductTypeCode) {
            this.ProductTypeCode = ProductTypeCode;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public String getCompanyCode() {
            return CompanyCode;
        }

        public void setCompanyCode(String CompanyCode) {
            this.CompanyCode = CompanyCode;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public int getProductTypeId() {
            return ProductTypeId;
        }

        public void setProductTypeId(int ProductTypeId) {
            this.ProductTypeId = ProductTypeId;
        }

        public int getMatchTypeId() {
            return MatchTypeId;
        }

        public void setMatchTypeId(int MatchTypeId) {
            this.MatchTypeId = MatchTypeId;
        }

        public String getMatchTypeName() {
            return MatchTypeName;
        }

        public void setMatchTypeName(String MatchTypeName) {
            this.MatchTypeName = MatchTypeName;
        }

        public int getMaterialTypeId() {
            return MaterialTypeId;
        }

        public void setMaterialTypeId(int MaterialTypeId) {
            this.MaterialTypeId = MaterialTypeId;
        }

        public String getMaterialTypeName() {
            return MaterialTypeName;
        }

        public void setMaterialTypeName(String MaterialTypeName) {
            this.MaterialTypeName = MaterialTypeName;
        }

        public String getFactoryProductNo() {
            return FactoryProductNo;
        }

        public void setFactoryProductNo(String FactoryProductNo) {
            this.FactoryProductNo = FactoryProductNo;
        }

        public Object getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(Object ProductPrice) {
            this.ProductPrice = ProductPrice;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
        }

        public int getProductStyleId() {
            return ProductStyleId;
        }

        public void setProductStyleId(int ProductStyleId) {
            this.ProductStyleId = ProductStyleId;
        }

        public List<ADPhotoesBean> getADPhotoes() {
            return ADPhotoes;
        }

        public void setADPhotoes(List<ADPhotoesBean> ADPhotoes) {
            this.ADPhotoes = ADPhotoes;
        }

        public List<DetailPhotoesBean> getDetailPhotoes() {
            return DetailPhotoes;
        }

        public void setDetailPhotoes(List<DetailPhotoesBean> DetailPhotoes) {
            this.DetailPhotoes = DetailPhotoes;
        }

        public static class ADPhotoesBean {
            /**
             * PhotoType : 269
             * PhotoSmall : /Images/ZL_Product/2018/05/10/2018051017304037_s.png
             * PhotoBig : /Images/ZL_Product/2018/05/10/2018051017304037_b.png
             */

            private int PhotoType;
            private String PhotoSmall;
            private String PhotoBig;

            public int getPhotoType() {
                return PhotoType;
            }

            public void setPhotoType(int PhotoType) {
                this.PhotoType = PhotoType;
            }

            public String getPhotoSmall() {
                return PhotoSmall;
            }

            public void setPhotoSmall(String PhotoSmall) {
                this.PhotoSmall = PhotoSmall;
            }

            public String getPhotoBig() {
                return PhotoBig;
            }

            public void setPhotoBig(String PhotoBig) {
                this.PhotoBig = PhotoBig;
            }
        }

        public static class DetailPhotoesBean {
            /**
             * PhotoType : 270
             * PhotoSmall : /Images/ZL_Product/2018/05/19/2018051911323308_s.png
             * PhotoBig : /Images/ZL_Product/2018/05/19/2018051911323308_b.png
             */

            private int PhotoType;
            private String PhotoSmall;
            private String PhotoBig;

            public int getPhotoType() {
                return PhotoType;
            }

            public void setPhotoType(int PhotoType) {
                this.PhotoType = PhotoType;
            }

            public String getPhotoSmall() {
                return PhotoSmall;
            }

            public void setPhotoSmall(String PhotoSmall) {
                this.PhotoSmall = PhotoSmall;
            }

            public String getPhotoBig() {
                return PhotoBig;
            }

            public void setPhotoBig(String PhotoBig) {
                this.PhotoBig = PhotoBig;
            }
        }
    }
}
