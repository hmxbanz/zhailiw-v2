package com.zhailiw.app.server.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailResponse {

    /**
     * state : 1
     * msg : 成功
     * data : {"OrderList":[{"UserInfoId":1475,"OrderAttributeID":155,"Quantity":1,"OriginalPrice":275000,"TotalPrice":275000,"PriceNow":275000,"Type":"实验","GPR":null,"FactoryPrice":110000,"ProductAttributeName":null,"Price":275000,"ProductAttributeId":22,"CompanyId":12,"ProductNo":"ZL12-F-57","ProductInfo":"[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 \n[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列\n[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列","ProductName":"ms085沙发","PhotoSmall":"/Images/ZL_Product/2018/05/23/2018052314311805_s.jpg","ProductId":57,"Color":"白","TypeName":null,"OrderID":116}],"UserInfoId":1475,"OrderState":280,"OrderType":278,"CreateDate":"/Date(1527497317173)/","Remark":null,"OrderNo":"116201805281648374551","AliPayNo":null,"WxPayNo":null,"OrderDoneDate":null,"OrderStateName":"未付款","OrderTypeName":"商城","OrderID":116,"Total":275000,"PayTotal":55000,"RebateTotal":27500,"LevelDiscount":0.8,"LevelRebate":0.1,"AddressId":null,"Contact":null,"Cellphone":null,"Address":null}
     * wxPayStr : {"appid":"wxb8d31ebd0f155f65","partnerid":"1503432121","noncestr":"8065D07DA4A77621450AA84FEE5656D9","sign":"158F2648EAF2B35A1E259DAB9D420A4A","prepayid":"wx29142154520971780f717ee13002743188","package":"Sign=WXPay","timestamp":"1527574914"}
     * aliPayStr :
     */

    private int state;
    private String msg;
    private DataBean data;
    private WxPayStrBean wxPayStr;
    private String aliPayStr;

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

    public WxPayStrBean getWxPayStr() {
        return wxPayStr;
    }

    public void setWxPayStr(WxPayStrBean wxPayStr) {
        this.wxPayStr = wxPayStr;
    }

    public String getAliPayStr() {
        return aliPayStr;
    }

    public void setAliPayStr(String aliPayStr) {
        this.aliPayStr = aliPayStr;
    }

    public static class DataBean {
        /**
         * OrderList : [{"UserInfoId":1475,"OrderAttributeID":155,"Quantity":1,"OriginalPrice":275000,"TotalPrice":275000,"PriceNow":275000,"Type":"实验","GPR":null,"FactoryPrice":110000,"ProductAttributeName":null,"Price":275000,"ProductAttributeId":22,"CompanyId":12,"ProductNo":"ZL12-F-57","ProductInfo":"[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 \n[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列\n[蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列","ProductName":"ms085沙发","PhotoSmall":"/Images/ZL_Product/2018/05/23/2018052314311805_s.jpg","ProductId":57,"Color":"白","TypeName":null,"OrderID":116}]
         * UserInfoId : 1475
         * OrderState : 280
         * OrderType : 278
         * CreateDate : /Date(1527497317173)/
         * Remark : null
         * OrderNo : 116201805281648374551
         * AliPayNo : null
         * WxPayNo : null
         * OrderDoneDate : null
         * OrderStateName : 未付款
         * OrderTypeName : 商城
         * OrderID : 116
         * Total : 275000
         * PayTotal : 55000
         * RebateTotal : 27500
         * LevelDiscount : 0.8
         * LevelRebate : 0.1
         * AddressId : null
         * Contact : null
         * Cellphone : null
         * Address : null
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
        private int PayTotal;
        private int RebateTotal;
        private double LevelDiscount;
        private double LevelRebate;
        private Object AddressId;
        private Object Contact;
        private Object Cellphone;
        private Object Address;
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

        public int getPayTotal() {
            return PayTotal;
        }

        public void setPayTotal(int PayTotal) {
            this.PayTotal = PayTotal;
        }

        public int getRebateTotal() {
            return RebateTotal;
        }

        public void setRebateTotal(int RebateTotal) {
            this.RebateTotal = RebateTotal;
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

        public Object getAddressId() {
            return AddressId;
        }

        public void setAddressId(Object AddressId) {
            this.AddressId = AddressId;
        }

        public Object getContact() {
            return Contact;
        }

        public void setContact(Object Contact) {
            this.Contact = Contact;
        }

        public Object getCellphone() {
            return Cellphone;
        }

        public void setCellphone(Object Cellphone) {
            this.Cellphone = Cellphone;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
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
             * OrderAttributeID : 155
             * Quantity : 1
             * OriginalPrice : 275000
             * TotalPrice : 275000
             * PriceNow : 275000
             * Type : 实验
             * GPR : null
             * FactoryPrice : 110000
             * ProductAttributeName : null
             * Price : 275000
             * ProductAttributeId : 22
             * CompanyId : 12
             * ProductNo : ZL12-F-57
             * ProductInfo : [蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木
             [蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列
             [蓝骑家居] 1.8米床 高档典雅 复古大气 进口杨木 双人床 莱克星顿系列
             * ProductName : ms085沙发
             * PhotoSmall : /Images/ZL_Product/2018/05/23/2018052314311805_s.jpg
             * ProductId : 57
             * Color : 白
             * TypeName : null
             * OrderID : 116
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

    public static class WxPayStrBean {
        /**
         * appid : wxb8d31ebd0f155f65
         * partnerid : 1503432121
         * noncestr : 8065D07DA4A77621450AA84FEE5656D9
         * sign : 158F2648EAF2B35A1E259DAB9D420A4A
         * prepayid : wx29142154520971780f717ee13002743188
         * package : Sign=WXPay
         * timestamp : 1527574914
         */

        private String appid;
        private String partnerid;
        private String noncestr;
        private String sign;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
