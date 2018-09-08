package com.zhailiw.app.server.response;

import java.util.List;

public class ProductAttributeResponse {

/**
* state : 1
* msg : 成功
* data : [{"ProductId":57,"ProductName":"ms085沙发","ProductInfo":null,"ProductNo":"ZL12-F-57","CompanyId":12,"ProductAttributeId":22,"Price":275000,"ProductAttributeName":null,"FactoryPrice":110000,"GPR":null,"Type":"实验","Color":"白","Name":null,"CreateDate":"/Date(1527057004053)/","PriceNow":275000,"PhotoSmall":"/Images/ZL_Product/2018/05/23/2018052314312150_s.jpg","ProductStyleId":null,"MatchTypeId":242,"ProductTypeId":248,"MaterialTypeId":267,"FactoryProductNo":null,"ProductTypeName":"沙发","MatchTypeName":"客厅","ProductStyleName":null,"MaterialTypeName":"纺织类"}]
*/

private  int state ;private  String msg ;private  List<DataBean> data ;public int   getState() {   return state ;}public void  setState( int state) {   this.state = state;}public String   getMsg() {   return msg ;}public void  setMsg( String msg) {   this.msg = msg;}public List<DataBean>   getData() {   return data ;}public void  setData( List<DataBean> data) {   this.data = data;}public static class DataBean{ /**
* ProductId : 57
* ProductName : ms085沙发
* ProductInfo : null
* ProductNo : ZL12-F-57
* CompanyId : 12
* ProductAttributeId : 22
* Price : 275000
* ProductAttributeName : null
* FactoryPrice : 110000
* GPR : null
* Type : 实验
* Color : 白
* Name : null
* CreateDate : /Date(1527057004053)/
* PriceNow : 275000
* PhotoSmall : /Images/ZL_Product/2018/05/23/2018052314312150_s.jpg
* ProductStyleId : null
* MatchTypeId : 242
* ProductTypeId : 248
* MaterialTypeId : 267
* FactoryProductNo : null
* ProductTypeName : 沙发
* MatchTypeName : 客厅
* ProductStyleName : null
* MaterialTypeName : 纺织类
*/

private  int ProductId ;
private  String ProductName ;
private  Object ProductInfo ;
private  String ProductNo ;
private  int CompanyId ;
private  int ProductAttributeId ;
private  int Price ;
private  Object ProductAttributeName ;
private  int FactoryPrice ;
private  Object GPR ;
private  String Type ;
private  String Color ;
private  Object Name ;
private  String CreateDate ;
private  int PriceNow ;
private  String PhotoSmall ;
private  Object ProductStyleId ;
private  int MatchTypeId ;
private  int ProductTypeId ;
private  int MaterialTypeId ;
private  Object FactoryProductNo ;
private  String ProductTypeName ;
private  String MatchTypeName ;
private  Object ProductStyleName ;
private  String MaterialTypeName ;
        private  boolean IsSelect ;

        public boolean getIsSelect() {
            return IsSelect;
        }

        public void setIsSelect(boolean select) {
            IsSelect = select;
        }

        public int   getProductId() {   return ProductId ;}
public void  setProductId( int ProductId) {   this.ProductId = ProductId;}
public String   getProductName() {   return ProductName ;}
public void  setProductName( String ProductName) {   this.ProductName = ProductName;}
public Object   getProductInfo() {   return ProductInfo ;}
public void  setProductInfo( Object ProductInfo) {   this.ProductInfo = ProductInfo;}
public String   getProductNo() {   return ProductNo ;}
public void  setProductNo( String ProductNo) {   this.ProductNo = ProductNo;}
public int   getCompanyId() {   return CompanyId ;}
public void  setCompanyId( int CompanyId) {   this.CompanyId = CompanyId;}
public int   getProductAttributeId() {   return ProductAttributeId ;}
public void  setProductAttributeId( int ProductAttributeId) {   this.ProductAttributeId = ProductAttributeId;}
public int   getPrice() {   return Price ;}
public void  setPrice( int Price) {   this.Price = Price;}
public Object   getProductAttributeName() {   return ProductAttributeName ;}
public void  setProductAttributeName( Object ProductAttributeName) {   this.ProductAttributeName = ProductAttributeName;}
public int   getFactoryPrice() {   return FactoryPrice ;}
public void  setFactoryPrice( int FactoryPrice) {   this.FactoryPrice = FactoryPrice;}
public Object   getGPR() {   return GPR ;}
public void  setGPR( Object GPR) {   this.GPR = GPR;}
public String   getType() {   return Type ;}
public void  setType( String Type) {   this.Type = Type;}
public String   getColor() {   return Color ;}public void  setColor( String Color) {   this.Color = Color;}public Object   getName() {   return Name ;}public void  setName( Object Name) {   this.Name = Name;}public String   getCreateDate() {   return CreateDate ;}public void  setCreateDate( String CreateDate) {   this.CreateDate = CreateDate;}public int   getPriceNow() {   return PriceNow ;}public void  setPriceNow( int PriceNow) {   this.PriceNow = PriceNow;}public String   getPhotoSmall() {   return PhotoSmall ;}public void  setPhotoSmall( String PhotoSmall) {   this.PhotoSmall = PhotoSmall;}public Object   getProductStyleId() {   return ProductStyleId ;}public void  setProductStyleId( Object ProductStyleId) {   this.ProductStyleId = ProductStyleId;}public int   getMatchTypeId() {   return MatchTypeId ;}public void  setMatchTypeId( int MatchTypeId) {   this.MatchTypeId = MatchTypeId;}public int   getProductTypeId() {   return ProductTypeId ;}public void  setProductTypeId( int ProductTypeId) {   this.ProductTypeId = ProductTypeId;}public int   getMaterialTypeId() {   return MaterialTypeId ;}public void  setMaterialTypeId( int MaterialTypeId) {   this.MaterialTypeId = MaterialTypeId;}public Object   getFactoryProductNo() {   return FactoryProductNo ;}public void  setFactoryProductNo( Object FactoryProductNo) {   this.FactoryProductNo = FactoryProductNo;}public String   getProductTypeName() {   return ProductTypeName ;}public void  setProductTypeName( String ProductTypeName) {   this.ProductTypeName = ProductTypeName;}public String   getMatchTypeName() {   return MatchTypeName ;}public void  setMatchTypeName( String MatchTypeName) {   this.MatchTypeName = MatchTypeName;}public Object   getProductStyleName() {   return ProductStyleName ;}public void  setProductStyleName( Object ProductStyleName) {   this.ProductStyleName = ProductStyleName;}public String   getMaterialTypeName() {   return MaterialTypeName ;}public void  setMaterialTypeName( String MaterialTypeName) {   this.MaterialTypeName = MaterialTypeName;}}}
