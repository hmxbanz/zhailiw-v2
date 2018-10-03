package com.zhailiw.app.server;

import android.content.Context;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhailiw.app.Const;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.common.json.JsonMananger;
import com.zhailiw.app.server.request.AddAddressRequest;
import com.zhailiw.app.server.request.BindPhoneRequest;
import com.zhailiw.app.server.request.LoginRequest;
import com.zhailiw.app.server.request.RegisterRequest;
import com.zhailiw.app.server.response.ADResponse;
import com.zhailiw.app.server.response.AddOrderResponse;
import com.zhailiw.app.server.response.AddressResponse;
import com.zhailiw.app.server.response.CaptchaResponse;
import com.zhailiw.app.server.response.CheckWxQqResponse;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.DecorateAllResponse;
import com.zhailiw.app.server.response.DecorateDetailResponse;
import com.zhailiw.app.server.response.DecorateListResponse;
import com.zhailiw.app.server.response.DefaultAddressResponse;
import com.zhailiw.app.server.response.FavorResponse;
import com.zhailiw.app.server.response.GalleryPicResponse;
import com.zhailiw.app.server.response.GalleryResponse;
import com.zhailiw.app.server.response.HouseDetailResponse;
import com.zhailiw.app.server.response.HousePeopleResponse;
import com.zhailiw.app.server.response.LoginResponse;
import com.zhailiw.app.server.response.OrderDetailResponse;
import com.zhailiw.app.server.response.ProductResponse;
import com.zhailiw.app.server.response.ProgressListResponse;
import com.zhailiw.app.server.response.ShopCarResponse;
import com.zhailiw.app.server.response.ShopResponse;
import com.zhailiw.app.server.response.StyleResponse;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.server.response.UserInfoResponse;
import com.zhailiw.app.server.response.ProductAttributeResponse;
import com.zhailiw.app.server.response.VersionResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Response;


/**
 * Created by AMing on 16/1/14.
 * Company RongCloud
 */
@SuppressWarnings("deprecation")
public class UserAction extends BaseAction {
    private final String TAG=UserAction.class.getSimpleName();
    private final String CONTENT_TYPE = "application/json";
    private final String ENCODING = "utf-8";
    public String token;
    public static UserAction instance;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public UserAction(Context context) {
        super(context);
        mContext = context;
    }
    public static UserAction getInstance(Context context) {
            if (instance == null) {
                synchronized (UserAction.class) {
                    if (instance == null) {
                        instance = new UserAction(context);
                    }
                }
            }

        return instance;
    }
    //检测微信QQ绑定
    public CheckWxQqResponse checkWxQq() throws HttpException {
        String uri = getURL("User/checkWXQQ");
        LinkedHashMap map=new LinkedHashMap<>();
        return getRequest(CheckWxQqResponse.class,map,uri);
    }
    //更新性别和出生日期
    public CommonResponse updateInfo(String sex,String birthday) throws HttpException {
        String uri = getURL("User/updateUserInfo");
        if("".equals(sex))
            sex="0";
        if("".equals(birthday))
            birthday="2018-1-1";
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("Sex",sex);
        map.put("Birthday",birthday);
        return getRequest(CommonResponse.class,map,uri);
    }
    //获取验证码
    public CaptchaResponse getCaptcha(String cellPhone,String type) throws HttpException
    {
        String uri = getURL("/Home/GetCaptcha");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("cellPhone",cellPhone);
        map.put("type",type);
        return getRequest(CaptchaResponse.class,map,uri);
    }
    //注册
    public CommonResponse register(String userName,String password, String nickName, String captcha) throws HttpException
    {
        String uri = getURL("User/Register");
        String json=JsonMananger.beanToJson(new RegisterRequest(userName,password,nickName,captcha));
        return postRequest(CommonResponse.class,json,uri);
    }
    //重置密码
    public CommonResponse resetPassword(String userName, String password, String captcha) throws HttpException
    {
        String uri = getURL("User/resetPassword");
        String json=JsonMananger.beanToJson(new RegisterRequest(userName,password,null,captcha));
        return postRequest(CommonResponse.class,json,uri);

    }
    //图库
    public GalleryResponse getGallery(String pageIndex,String galleryTypeId,String type) throws HttpException {
        String uri = getURL("Home/getGallery");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("galleryTypeId",galleryTypeId);
        map.put("type",type);
        return getRequest(GalleryResponse.class,map,uri);
    }
    //图库图片
    public GalleryPicResponse getGalleryPic(String galleryId) throws HttpException {
        String uri = getURL("Home/getGalleryPic");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("galleryId",galleryId);
        return getRequest(GalleryPicResponse.class,map,uri);
    }
    //登录
    public LoginResponse login(String userName, String password, String openId, String type) throws HttpException {
        String uri = getURL("User/Login");
        String json=JsonMananger.beanToJson(new LoginRequest(userName,password,openId,type));
        return postRequest(LoginResponse.class,json,uri);
    }
    //取风格
    public StyleResponse getStyles() throws HttpException {
        String uri = getURL("User/getClients");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex","1");
        map.put("keyword","");
        return getRequest(StyleResponse.class,map,uri);
    }
    //上传头像
    public CommonResponse uploadAvatar(File imgFile) throws HttpException {
        String uri = getURL("User/updateAvatar");
        HashMap<String,String> params= new HashMap<>();
        return postFormRequest(CommonResponse.class,params,"avatar", "imgFile.jpg",imgFile,uri);
    }
    //取个人资料
    public UserInfoResponse getInfo() throws HttpException {
        String uri = getURL("User/getMyInfo");
        LinkedHashMap map=new LinkedHashMap<>();
        return getRequest(UserInfoResponse.class,map,uri);
    }
    //修改个人资料
    public CommonResponse save(String nickName) throws HttpException {
        String uri = getURL("User/updateUserInfo");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("nickName",nickName);
        return getRequest(CommonResponse.class,map,uri);
    }
    //取收货地址
    public AddressResponse getAddress() throws HttpException{
        String uri = getURL("User/getMyAddress");
        return getRequest(AddressResponse.class,null,uri);
    }
    //删除收货地址
    public CommonResponse delAddress(int delAddressId) throws HttpException{
        String uri = getURL("User/removeAddress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("addressId",delAddressId+"");
        return getRequest(CommonResponse.class,map,uri);
    }
    //设置默认收货地址
    public CommonResponse setAddress(int delAddressId) throws HttpException{
        String uri = getURL("User/setDefaultAddress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("addressId",delAddressId+"");
        return getRequest(CommonResponse.class,map,uri);
    }
    //新增收货地址
    public CommonResponse addAddress(String contact, String cellphone, String address, boolean checked) throws HttpException{
        String uri = getURL("User/addAddress");
        String json=JsonMananger.beanToJson(new AddAddressRequest(contact,cellphone,address,checked));
        return postRequest(CommonResponse.class,json,uri);
    }
    //取商品列表
    public ShopResponse getProducts(String pageIndex, String styleId, String productTypeId) throws HttpException{
        String uri = getURL("Home/getProducts");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("styleId",styleId);
        map.put("productTypeId",productTypeId);
        return getRequest(ShopResponse.class,map,uri);
    }
    //取购物车
    public ShopCarResponse getMyOrder(String pageIndex, String orderState, String orderType) throws HttpException{
        String uri = getURL("User/getMyOrders");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("orderState",orderState);
        map.put("orderType",orderType);
        return getRequest(ShopCarResponse.class,map,uri);
    }
    //取广告
    public ADResponse getAds(String type) throws HttpException{
        String uri = getURL("Home/getAds");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("type",type);
        return getRequest(ADResponse.class,map,uri);
    }
    //手机绑定
    public CommonResponse bindPhone(String userName, String captcha,String openId, String bindType ) throws HttpException
    {
        String uri = getURL("/User/ThirdPartBind");
        String json=JsonMananger.beanToJson(new BindPhoneRequest(userName,captcha,openId,bindType));
        return postRequest(CommonResponse.class,json,uri);

    }
    //取系统对象
    public SystemObjResponse getSystemObj() throws HttpException {
        String uri = getURL("/Sys/GetSysObj");
        return getRequest(SystemObjResponse.class,null,uri);
    }

//取收藏
    public FavorResponse getFavors(String pageIndex) throws HttpException {
        String uri = getURL("User/getMyFavors");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex",pageIndex);
        return getRequest(FavorResponse.class,map,uri);
    }

    public CommonResponse updateBuyShop(int orderAttributeId, int count) throws  HttpException{
        String uri = getURL("User/updateBuyShop");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("orderAttributeId",orderAttributeId+"");
        map.put("count",count+"");
        return getRequest(CommonResponse.class,map,uri);
    }
    public CommonResponse deleteBuyShop(String orderAttributeIds) throws  HttpException{
        String uri = getURL("User/deleteBuyShop");
        String json="{\"orderAttributeIds\":"+orderAttributeIds+"}";
        return postRequest(CommonResponse.class,json,uri);
    }
    public AddOrderResponse payBuyShop(String orderAttributeIds) throws  HttpException{
        String uri = getURL("User/addOrderFromShopCar");
        String json="{\"orderAttributeIds\":"+orderAttributeIds+"}";
        return postRequest(AddOrderResponse.class,json,uri);
    }
//取产品
    public ProductResponse getProduct(String productId) throws HttpException{
        String uri = getURL("Home/getProduct");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("productId",productId);
        return getRequest(ProductResponse.class,map,uri);
    }
    //取产品规格
    public ProductAttributeResponse getProductAttribute(String productId) throws HttpException{
        String uri = getURL("Home/getProductAttribute");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("productId",productId);
        return getRequest(ProductAttributeResponse.class,map,uri);
    }
//收藏商品
    public CommonResponse addFavor(String productId) throws HttpException{
        String uri = getURL("User/addMyFavors");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("productId",productId);
        return getRequest(CommonResponse.class,map,uri);
    }
    //加入购物车
    public AddOrderResponse addOrderCar(String orderType, String quantity, String productAttributeId) throws HttpException{
        String uri = getURL("User/addOrder");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("orderType",orderType);
        map.put("quantity",quantity);
        map.put("productAttributeId",productAttributeId);
        return getRequest(AddOrderResponse.class,map,uri);
    }

    public OrderDetailResponse getOrderDetail(String orderId) throws HttpException {
        String uri = getURL("User/payOrder");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("orderId",orderId);
        return getRequest(OrderDetailResponse.class,map,uri);
    }
    public DefaultAddressResponse getOrderAddress(String addressId) throws HttpException {
        String uri = getURL("User/getOrderAddress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("addressId",addressId);
        return getRequest(DefaultAddressResponse.class,map,uri);
    }

    public CommonResponse removeOrder(String removeOrderId) throws HttpException {
        String uri = getURL("User/removeOrder");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("orderId",removeOrderId);
        return getRequest(CommonResponse.class,map,uri);
    }

    public CommonResponse setOrderAddress(String orderId, String addressId) throws HttpException{
        String uri = getURL("User/updateOrderAddress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("orderId",orderId);
        map.put("addressId",addressId);
        return getRequest(CommonResponse.class,map,uri);
    }

    //版本检查
    public VersionResponse checkVersion() throws HttpException {
        String uri = Const.GETVERSION;
        LinkedHashMap map=new LinkedHashMap<>();
        return getRequest(VersionResponse.class,map,uri);
    }

    //取施工装修房列表
    public DecorateAllResponse getDecorate(String pageIndex, String state) throws HttpException{
        String uri = getURL("User/getHouseDecorates");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("state",state);
        return getRequest(DecorateAllResponse.class,map,uri);
    }
    //取装修进程列表
    public DecorateDetailResponse getDecorateDetail(String houseId) throws HttpException{
        String uri = getURL("User/getDecorate");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("houseId",houseId);
        return getRequest(DecorateDetailResponse.class,map,uri);
    }
    //取装修进程>装修设计列表
    public DecorateListResponse getDecorateList(String processId) throws HttpException{
        String uri = getURL("User/getProcessDetail");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("processId",processId);
        return getRequest(DecorateListResponse.class,map,uri);
    }
    //取施工流程详情列表
    public DecorateListResponse getProgressDiary(String progressId) throws HttpException{
        String uri = getURL("User/getProgresses");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("progressId",progressId);
        return getRequest(DecorateListResponse.class,map,uri);
    }
    //取装施工流程项(泥，木项)
    public ProgressListResponse getProgressList(String processId) throws HttpException{
        String uri = getURL("User/getProcesses");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("processId",processId);
        return getRequest(ProgressListResponse.class,map,uri);
    }

    //删除施工流程项
    public CommonResponse delProgress(String progressId) throws HttpException{
        String uri = getURL("User/delProgress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("progressId",progressId);
        return getRequest(CommonResponse.class,map,uri);
    }

    //取装修房详情
    public HouseDetailResponse getHouseDetail(String houseId) throws HttpException{
        String uri = getURL("User/getHouseDetail");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("houseId",houseId);
        return getRequest(HouseDetailResponse.class,map,uri);
    }

    //取装施工流程项(泥，木项)
    public CommonResponse updateProgressOrder(String newID,String newOrder,String oldID,String oldOrder) throws HttpException{
        String uri = getURL("User/updateProgressOrder");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("newID",newID);
        map.put("newOrder",newOrder);
        map.put("oldID",oldID);
        map.put("oldOrder",oldOrder);
        return getRequest(CommonResponse.class,map,uri);
    }
    //新建施工流程
    public CommonResponse addProgress(int processId, String name, String startDate, String endDate) throws HttpException{
        String uri = getURL("User/addHouseProgress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("ProcessID",processId+"");
        map.put("Name",name);
        map.put("StartDate",startDate);
        map.put("EndDate",endDate);
        return getRequest(CommonResponse.class,map,uri);
    }
    //修改施工流程
    public CommonResponse updateProgress(int progressId, String name, String startDate, String endDate) throws HttpException{
        String uri = getURL("User/updateProgress");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("progressId",progressId+"");
        map.put("Name",name);
        map.put("StartDate",startDate);
        map.put("EndDate",endDate);
        return getRequest(CommonResponse.class,map,uri);
    }

    //发新日志
    public CommonResponse addDiary(String diaryType,String processId,String progressId,String remark, File imgFile,File imgFile2,File imgFile3,File imgFile4,File imgFile5,File imgFile6,File imgFile7,File imgFile8,File imgFile9) throws HttpException {
        String uri = getURL("/User/addWorkingLog");
        HashMap<String, String> params = new HashMap<>();
        params.put("DetailType", diaryType);
        if(processId !=null)
        params.put("processId", processId);
        if(progressId !=null)
        params.put("progressId", progressId);
        params.put("remark", remark);
        return postFormRequest(CommonResponse.class, params, "Pic1", "Pic1.jpg", imgFile
                , "Pic2", "Pic2.jpg", imgFile2
                , "Pic3", "Pic3.jpg", imgFile3
                , "Pic4", "Pic4.jpg", imgFile4
                , "Pic5", "Pic5.jpg", imgFile5
                , "Pic6", "Pic6.jpg", imgFile6
                , "Pic7", "Pic7.jpg", imgFile7
                , "Pic8", "Pic8.jpg", imgFile8
                , "Pic9", "Pic9.jpg", imgFile9
                ,uri);
    }
    //设置置顶，作废
    public CommonResponse updateWorkLogState(String Id,String type) throws HttpException{
        String uri = getURL("User/updateWorkLogState");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("Id",Id);
        map.put("type",type);
        return getRequest(CommonResponse.class,map,uri);
    }

    //增加项目相关人员
    public CommonResponse addDecoratePeople(String decorateId, String type, String realName, String cellphone) throws HttpException{
        String uri = getURL("User/addDecoratePeople");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("decorateId",decorateId);
        map.put("type",type);
        map.put("realName",realName);
        map.put("cellphone",cellphone);
        return getRequest(CommonResponse.class,map,uri);
    }
    //设置装修进程状态
    public CommonResponse setProcessState(String processId,String state) throws HttpException{
        String uri = getURL("User/updateProcessState");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("processId",processId);
        map.put("state",state);
        return getRequest(CommonResponse.class,map,uri);
    }
    //设计施工流程状态
    public CommonResponse setProgressState(String progressId,String state) throws HttpException{
        String uri = getURL("User/updateProgressState");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("progressId",progressId);
        map.put("state",state);
        return getRequest(CommonResponse.class,map,uri);
    }

    //取装修相关人员
    public HousePeopleResponse getHousePeoples(String houseId) throws HttpException{
        String uri = getURL("User/getHousePeoples");
        LinkedHashMap map=new LinkedHashMap<>();
        map.put("houseId",houseId);
        return getRequest(HousePeopleResponse.class,map,uri);
    }

    //get请求
    public <T> T getRequest(Class<T> t, LinkedHashMap map, String uri)throws HttpException
    {
        String result="";
        Response response=null;
        try {
            response=OkHttpUtils
                    .get()
                    .addHeader("token",token)
                    .params(map)
                    .url(uri)
                    .build()
                    .execute();
            result =response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.d("请求的：%s",map);
        Logger.d(t.getSimpleName()+"："+ result);
        T beanResponse = null;
        try {
            if(t.getSimpleName().equals("SystemObjResponse") || t.getSimpleName().equals("CityResponse"))
                beanResponse= new Gson().fromJson(result,t);
            else
                beanResponse = JsonMananger.jsonToBean(result, t);
        }
        catch (JSONException e) {
            NToast.shortToast(mContext,result);
            Logger.e(TAG, t.getSimpleName()+" occurs JSONException e=" + e.toString());
            return null;
        }
        return beanResponse;
    }
    //post请求
    public <T> T postRequest(Class<T> t, String json, String uri)throws HttpException
    {
        String result="";
        Response response=null;
        try {
            response=OkHttpUtils
                    .postString()
                    .addHeader("token",token)
                    .url(uri)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(json)//.content(new Gson().toJson(new User("zhy", "123")))
                    .build()
                    .execute();
            result =response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.d("请求的：%s",json);
        Logger.d(t.getSimpleName()+"："+ result);

        T beanResponse = null;
        try {
            beanResponse = JsonMananger.jsonToBean(result, t);
        } catch (JSONException e) {
            Logger.e(TAG, t.getSimpleName()+" occurs JSONException e=" + e.toString());
            return null;
        }
        return beanResponse;
    }
    //post请求
    public <T> T postFormRequest(Class<T> t, Map<String, String> params, String fileKey, String fileName, File file, String uri)throws HttpException
    {
        String result="";
        Response response=null;
        try {
            response=OkHttpUtils
                    .post()
                    .addHeader("token",token)
                    .params(params)
                    .addFile(fileKey, fileName,file)
                    .url(uri)
                    .build()
                    .execute();
            result =response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.d("请求的：%s",params);
        Logger.d(t.getSimpleName()+"："+ result);

        T beanResponse = null;
        try {
            beanResponse = JsonMananger.jsonToBean(result, t);
        } catch (JSONException e) {
            Logger.e(TAG, t.getSimpleName()+" occurs JSONException e=" + e.toString());
            return null;
        }
        return beanResponse;
    }
    //post含九个文件请求
    public <T> T postFormRequest(Class<T> t, Map<String, String> params, String fileKey, String fileName, File file, String fileKey2, String fileName2, File file2
            , String fileKey3, String fileName3, File file3
            , String fileKey4, String fileName4, File file4
            , String fileKey5, String fileName5, File file5
            , String fileKey6, String fileName6, File file6
            , String fileKey7, String fileName7, File file7
            , String fileKey8, String fileName8, File file8
            , String fileKey9, String fileName9, File file9
            , String uri)throws HttpException
    {
        String result="";
        Response response=null;
        try {
            PostFormBuilder requestBuiler = OkHttpUtils
                    .post()
                    .addHeader("token", token)
                    .params(params);

            if(file !=null)
                requestBuiler.addFile(fileKey, fileName,file);
            if(file2 !=null)
                    requestBuiler.addFile(fileKey2, fileName2,file2);
            if(file3 !=null)
                    requestBuiler.addFile(fileKey3, fileName3,file3);
            if(file4 !=null)
                requestBuiler.addFile(fileKey4, fileName4,file4);
            if(file5 !=null)
                requestBuiler.addFile(fileKey5, fileName5,file5);
            if(file6 !=null)
                requestBuiler.addFile(fileKey6, fileName6,file6);
            if(file7 !=null)
                requestBuiler.addFile(fileKey7, fileName7,file7);
            if(file8 !=null)
                requestBuiler.addFile(fileKey8, fileName8,file8);
            if(file9 !=null)
                requestBuiler.addFile(fileKey9, fileName9,file9);
            response=requestBuiler
                    .url(uri)
                    .build()
                    .execute();
            result =response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.d("请求的：%s",params);
        Logger.d(t.getSimpleName()+"："+ result);

        T beanResponse = null;
        try {
            beanResponse = JsonMananger.jsonToBean(result, t);
        } catch (JSONException e) {
            Logger.e(TAG, t.getSimpleName()+" occurs JSONException e=" + e.toString());
            return null;
        }
        return beanResponse;
    }


}
