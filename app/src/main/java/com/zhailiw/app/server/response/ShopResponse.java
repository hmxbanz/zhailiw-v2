package com.zhailiw.app.server.response;

import java.util.List;

public class ShopResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 1
     * data : [{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":1,"CompanyName":"劲凯客厅家具","CompanyAddr":"广东省","Tel":"8888888","CellPhone":"13713713713","ProductId":49,"ProductNo":"J001-F-49","ProductName":"第十个","ProductInfo":"第十个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"陈先生","CompanyCode":"J001","CreateDate":"/Date(1525944640367)/","ProductTypeId":248,"MatchTypeId":244,"MatchTypeName":"卧室","MaterialTypeId":262,"MaterialTypeName":"金属架类","FactoryProductNo":"第十个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017304037_s.png","ProductStyleId":235,"ProductStyleName":"欧美风格"},{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":1,"CompanyName":"劲凯客厅家具","CompanyAddr":"广东省","Tel":"8888888","CellPhone":"13713713713","ProductId":48,"ProductNo":"J001-F-48","ProductName":"第九个","ProductInfo":"第九个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"陈先生","CompanyCode":"J001","CreateDate":"/Date(1525944616593)/","ProductTypeId":248,"MatchTypeId":244,"MatchTypeName":"卧室","MaterialTypeId":261,"MaterialTypeName":"板材类","FactoryProductNo":"第九个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017301660_s.png","ProductStyleId":235,"ProductStyleName":"欧美风格"},{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":1,"CompanyName":"劲凯客厅家具","CompanyAddr":"广东省","Tel":"8888888","CellPhone":"13713713713","ProductId":47,"ProductNo":"J001-F-47","ProductName":"第八个","ProductInfo":"第八个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"陈先生","CompanyCode":"J001","CreateDate":"/Date(1525944370807)/","ProductTypeId":248,"MatchTypeId":244,"MatchTypeName":"卧室","MaterialTypeId":263,"MaterialTypeName":"天然石类","FactoryProductNo":"第八个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017261081_s.png","ProductStyleId":235,"ProductStyleName":"欧美风格"},{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":1,"CompanyName":"劲凯客厅家具","CompanyAddr":"广东省","Tel":"8888888","CellPhone":"13713713713","ProductId":46,"ProductNo":"J001-F-46","ProductName":"第七个","ProductInfo":"第七个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"陈先生","CompanyCode":"J001","CreateDate":"/Date(1525944343523)/","ProductTypeId":248,"MatchTypeId":null,"MatchTypeName":null,"MaterialTypeId":null,"MaterialTypeName":null,"FactoryProductNo":"第七个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017254353_s.png","ProductStyleId":235,"ProductStyleName":"欧美风格"},{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":10,"CompanyName":"宅里","CompanyAddr":"广东省","Tel":null,"CellPhone":"13729213015","ProductId":45,"ProductNo":"ZL10-F-45","ProductName":"第六个","ProductInfo":"第六个","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"黄","CompanyCode":"ZL10","CreateDate":"/Date(1525944310770)/","ProductTypeId":248,"MatchTypeId":null,"MatchTypeName":null,"MaterialTypeId":null,"MaterialTypeName":null,"FactoryProductNo":"第六个","ProductPrice":null,"ProductImage":"/Images/ZL_Product/2018/05/10/2018051017251078_s.png","ProductStyleId":235,"ProductStyleName":"欧美风格"},{"ADPhotoes":null,"DetailPhotoes":null,"CompanyId":10,"CompanyName":"宅里","CompanyAddr":"广东省","Tel":null,"CellPhone":"13729213015","ProductId":35,"ProductNo":"ZL10-F-35","ProductName":"沙发","ProductInfo":"豪华版","ProductTypeName":"沙发","ProductTypeCode":"F","Contact":"黄","CompanyCode":"ZL10","CreateDate":"/Date(1523846431467)/","ProductTypeId":248,"MatchTypeId":244,"MatchTypeName":"卧室","MaterialTypeId":261,"MaterialTypeName":"板材类","FactoryProductNo":null,"ProductPrice":143,"ProductImage":"/Images/ZL_Product/2018/04/16/2018041610403149_s.jpg","ProductStyleId":235,"ProductStyleName":"欧美风格"}]
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
         * ADPhotoes : null
         * DetailPhotoes : null
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
         * ProductStyleName : 欧美风格
         */

        private Object ADPhotoes;
        private Object DetailPhotoes;
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
        private String ProductStyleName;

        public Object getADPhotoes() {
            return ADPhotoes;
        }

        public void setADPhotoes(Object ADPhotoes) {
            this.ADPhotoes = ADPhotoes;
        }

        public Object getDetailPhotoes() {
            return DetailPhotoes;
        }

        public void setDetailPhotoes(Object DetailPhotoes) {
            this.DetailPhotoes = DetailPhotoes;
        }

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

        public String getProductStyleName() {
            return ProductStyleName;
        }

        public void setProductStyleName(String ProductStyleName) {
            this.ProductStyleName = ProductStyleName;
        }
    }
}
