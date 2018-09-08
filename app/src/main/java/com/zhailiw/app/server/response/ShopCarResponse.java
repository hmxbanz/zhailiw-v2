package com.zhailiw.app.server.response;

import java.util.List;

public class ShopCarResponse {

    /**
     * state : 1
     * msg : 成功
     * totalPages : 1
     * data : [{"OrderList":[{"UserInfoId":1475,"OrderAttributeID":7,"Quantity":1,"OriginalPrice":143,"TotalPrice":143,"PriceNow":143,"Type":"三件套","GPR":null,"FactoryPrice":100,"ProductAttributeName":null,"Price":250,"ProductAttributeId":16,"CompanyId":10,"ProductNo":"ZL10-F-35","ProductInfo":"豪华版","ProductName":"沙发","PhotoSmall":"/Images/ZL_Product/2018/04/20/2018042011114800_s.jpg","ProductId":35,"Color":"红色","TypeName":null,"OrderID":7}],"UserInfoId":1475,"OrderState":280,"OrderType":289,"CreateDate":"/Date(1526716810293)/","Remark":null,"OrderNo":"7201805191600107016","AliPayNo":null,"WxPayNo":null,"OrderDoneDate":null,"OrderStateName":"未付款","OrderTypeName":"购物车","OrderID":7,"Total":143,"PayTotal":null,"RebateTotal":null,"LevelDiscount":null,"LevelRebate":null}]
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
         * OrderList : [{"UserInfoId":1475,"OrderAttributeID":7,"Quantity":1,"OriginalPrice":143,"TotalPrice":143,"PriceNow":143,"Type":"三件套","GPR":null,"FactoryPrice":100,"ProductAttributeName":null,"Price":250,"ProductAttributeId":16,"CompanyId":10,"ProductNo":"ZL10-F-35","ProductInfo":"豪华版","ProductName":"沙发","PhotoSmall":"/Images/ZL_Product/2018/04/20/2018042011114800_s.jpg","ProductId":35,"Color":"红色","TypeName":null,"OrderID":7}]
         * UserInfoId : 1475
         * OrderState : 280
         * OrderType : 289
         * CreateDate : /Date(1526716810293)/
         * Remark : null
         * OrderNo : 7201805191600107016
         * AliPayNo : null
         * WxPayNo : null
         * OrderDoneDate : null
         * OrderStateName : 未付款
         * OrderTypeName : 购物车
         * OrderID : 7
         * Total : 143
         * PayTotal : null
         * RebateTotal : null
         * LevelDiscount : null
         * LevelRebate : null
         */

        private int UserInfoId;
        private int OrderState;
        private int OrderType;
        private String CreateDate;
        private Object Remark;
        private String OrderNo;
        private Object AliPayNo;
        private Object WxPayNo;
        private Object OrderDoneDate;
        private String OrderStateName;
        private String OrderTypeName;
        private int OrderID;
        private int Total;
        private Object PayTotal;
        private Object RebateTotal;
        private Object LevelDiscount;
        private Object LevelRebate;
        private List<OrderListBean> OrderList;

        public int getUserInfoId() {
            return UserInfoId;
        }

        public void setUserInfoId(int UserInfoId) {
            this.UserInfoId = UserInfoId;
        }

        public int getOrderState() {
            return OrderState;
        }

        public void setOrderState(int OrderState) {
            this.OrderState = OrderState;
        }

        public int getOrderType() {
            return OrderType;
        }

        public void setOrderType(int OrderType) {
            this.OrderType = OrderType;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public Object getAliPayNo() {
            return AliPayNo;
        }

        public void setAliPayNo(Object AliPayNo) {
            this.AliPayNo = AliPayNo;
        }

        public Object getWxPayNo() {
            return WxPayNo;
        }

        public void setWxPayNo(Object WxPayNo) {
            this.WxPayNo = WxPayNo;
        }

        public Object getOrderDoneDate() {
            return OrderDoneDate;
        }

        public void setOrderDoneDate(Object OrderDoneDate) {
            this.OrderDoneDate = OrderDoneDate;
        }

        public String getOrderStateName() {
            return OrderStateName;
        }

        public void setOrderStateName(String OrderStateName) {
            this.OrderStateName = OrderStateName;
        }

        public String getOrderTypeName() {
            return OrderTypeName;
        }

        public void setOrderTypeName(String OrderTypeName) {
            this.OrderTypeName = OrderTypeName;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public Object getPayTotal() {
            return PayTotal;
        }

        public void setPayTotal(Object PayTotal) {
            this.PayTotal = PayTotal;
        }

        public Object getRebateTotal() {
            return RebateTotal;
        }

        public void setRebateTotal(Object RebateTotal) {
            this.RebateTotal = RebateTotal;
        }

        public Object getLevelDiscount() {
            return LevelDiscount;
        }

        public void setLevelDiscount(Object LevelDiscount) {
            this.LevelDiscount = LevelDiscount;
        }

        public Object getLevelRebate() {
            return LevelRebate;
        }

        public void setLevelRebate(Object LevelRebate) {
            this.LevelRebate = LevelRebate;
        }

        public List<OrderListBean> getOrderList() {
            return OrderList;
        }

        public void setOrderList(List<OrderListBean> OrderList) {
            this.OrderList = OrderList;
        }

        public static class OrderListBean {
            /**
             * UserInfoId : 1475
             * OrderAttributeID : 7
             * Quantity : 1
             * OriginalPrice : 143
             * TotalPrice : 143
             * PriceNow : 143
             * Type : 三件套
             * GPR : null
             * FactoryPrice : 100
             * ProductAttributeName : null
             * Price : 250
             * ProductAttributeId : 16
             * CompanyId : 10
             * ProductNo : ZL10-F-35
             * ProductInfo : 豪华版
             * ProductName : 沙发
             * PhotoSmall : /Images/ZL_Product/2018/04/20/2018042011114800_s.jpg
             * ProductId : 35
             * Color : 红色
             * TypeName : null
             * OrderID : 7
             */

            private int UserInfoId;
            private int OrderAttributeID;
            private int Quantity;
            private int OriginalPrice;
            private int TotalPrice;
            private int PriceNow;
            private String Type;
            private Object GPR;
            private int FactoryPrice;
            private Object ProductAttributeName;
            private int Price;
            private int ProductAttributeId;
            private int CompanyId;
            private String ProductNo;
            private String ProductInfo;
            private String ProductName;
            private String PhotoSmall;
            private int ProductId;
            private String Color;
            private Object TypeName;
            private int OrderID;
            private boolean IsCheck;

            public boolean isCheck() {
                return IsCheck;
            }

            public void setCheck(boolean check) {
                IsCheck = check;
            }

            public int getUserInfoId() {
                return UserInfoId;
            }

            public void setUserInfoId(int UserInfoId) {
                this.UserInfoId = UserInfoId;
            }

            public int getOrderAttributeID() {
                return OrderAttributeID;
            }

            public void setOrderAttributeID(int OrderAttributeID) {
                this.OrderAttributeID = OrderAttributeID;
            }

            public int getQuantity() {
                return Quantity;
            }

            public void setQuantity(int Quantity) {
                this.Quantity = Quantity;
            }

            public int getOriginalPrice() {
                return OriginalPrice;
            }

            public void setOriginalPrice(int OriginalPrice) {
                this.OriginalPrice = OriginalPrice;
            }

            public int getTotalPrice() {
                return TotalPrice;
            }

            public void setTotalPrice(int TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

            public int getPriceNow() {
                return PriceNow;
            }

            public void setPriceNow(int PriceNow) {
                this.PriceNow = PriceNow;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public Object getGPR() {
                return GPR;
            }

            public void setGPR(Object GPR) {
                this.GPR = GPR;
            }

            public int getFactoryPrice() {
                return FactoryPrice;
            }

            public void setFactoryPrice(int FactoryPrice) {
                this.FactoryPrice = FactoryPrice;
            }

            public Object getProductAttributeName() {
                return ProductAttributeName;
            }

            public void setProductAttributeName(Object ProductAttributeName) {
                this.ProductAttributeName = ProductAttributeName;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public int getProductAttributeId() {
                return ProductAttributeId;
            }

            public void setProductAttributeId(int ProductAttributeId) {
                this.ProductAttributeId = ProductAttributeId;
            }

            public int getCompanyId() {
                return CompanyId;
            }

            public void setCompanyId(int CompanyId) {
                this.CompanyId = CompanyId;
            }

            public String getProductNo() {
                return ProductNo;
            }

            public void setProductNo(String ProductNo) {
                this.ProductNo = ProductNo;
            }

            public String getProductInfo() {
                return ProductInfo;
            }

            public void setProductInfo(String ProductInfo) {
                this.ProductInfo = ProductInfo;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public String getPhotoSmall() {
                return PhotoSmall;
            }

            public void setPhotoSmall(String PhotoSmall) {
                this.PhotoSmall = PhotoSmall;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public String getColor() {
                return Color;
            }

            public void setColor(String Color) {
                this.Color = Color;
            }

            public Object getTypeName() {
                return TypeName;
            }

            public void setTypeName(Object TypeName) {
                this.TypeName = TypeName;
            }

            public int getOrderID() {
                return OrderID;
            }

            public void setOrderID(int OrderID) {
                this.OrderID = OrderID;
            }
        }
    }
}
