package com.zhailiw.app.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhailiw.app.Adapter.FullyGridLayoutManager;
import com.zhailiw.app.Adapter.GridImageAdapter;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;
import com.zhailiw.app.common.NToast;
import com.zhailiw.app.listener.AlertDialogCallBack;
import com.zhailiw.app.server.HttpException;
import com.zhailiw.app.server.async.OnDataListener;
import com.zhailiw.app.server.response.CommonResponse;
import com.zhailiw.app.server.response.SystemObjResponse;
import com.zhailiw.app.view.activity.DiaryDetailActivity;
import com.zhailiw.app.view.activity.DiaryNewActivity;
import com.zhailiw.app.widget.DialogWithYesOrNoUtils;
import com.zhailiw.app.widget.LoadDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class DiaryNewPresenter extends BasePresenter implements OnDataListener, View.OnClickListener {
    private static final int ADDDIARY = 1,ADDDIARYFORPROGRESS=2;
    private final int processId,progressId,type;
    private RecyclerView recyclerView;
    private DiaryNewActivity activity;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private int aspect_ratio_x=3, aspect_ratio_y=4;
    private File selectedFile1,selectedFile2,selectedFile3,selectedFile4,selectedFile5,selectedFile6,selectedFile7,selectedFile8,selectedFile9;
    private EditText Remark;
    private TextView txtDiaryType;
    private int diaryType;

    public DiaryNewPresenter(Context context){
        super(context);
        activity = (DiaryNewActivity) context;
        Intent intent=activity.getIntent();
        processId=intent.getIntExtra("processId",0);
        progressId=intent.getIntExtra("progressId",0);
        type=intent.getIntExtra("type",0);
    }

    public void init() {
        this.recyclerView = activity.findViewById(R.id.recycler);
        activity.findViewById(R.id.btn_new_diary).setOnClickListener(this);
        activity.findViewById(R.id.img_down).setOnClickListener(this);
        this.txtDiaryType=activity.findViewById(R.id.txt_diary_type);

        this.Remark=activity.findViewById(R.id.edit_remark);
        themeId = R.style.picture_default_style;
        FullyGridLayoutManager manager = new FullyGridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(activity, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(activity).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(activity).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(activity).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(activity)
                        .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(true)// 是否可预览视频
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(false ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }

    };


    public void onReturn(Intent data) {
        // 图片选择结果回调
        selectList = PictureSelector.obtainMultipleResult(data);
        // 例如 LocalMedia 里面返回三种path
        // 1.media.getPath(); 为原图path
        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
        // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
        for (LocalMedia media : selectList) {
            Log.i("图片-----》", media.getCompressPath());
        }
        if(selectList.size()==1)
            selectedFile1=new File(selectList.get(0).getCompressPath());
        else if(selectList.size()==2)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
        }
        else if(selectList.size()==3)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
        }
        else if(selectList.size()==4)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
        }
        else if(selectList.size()==5)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
            selectedFile5=new File(selectList.get(4).getCompressPath());
        }
        else if(selectList.size()==6)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
            selectedFile5=new File(selectList.get(4).getCompressPath());
            selectedFile6=new File(selectList.get(5).getCompressPath());
        }
        else if(selectList.size()==7)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
            selectedFile5=new File(selectList.get(4).getCompressPath());
            selectedFile6=new File(selectList.get(5).getCompressPath());
            selectedFile7=new File(selectList.get(6).getCompressPath());
        }
        else if(selectList.size()==8)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
            selectedFile5=new File(selectList.get(4).getCompressPath());
            selectedFile6=new File(selectList.get(5).getCompressPath());
            selectedFile7=new File(selectList.get(6).getCompressPath());
            selectedFile8=new File(selectList.get(7).getCompressPath());
        }
        else if(selectList.size()==9)
        {
            selectedFile1=new File(selectList.get(0).getCompressPath());
            selectedFile2=new File(selectList.get(1).getCompressPath());
            selectedFile3=new File(selectList.get(2).getCompressPath());
            selectedFile4=new File(selectList.get(3).getCompressPath());
            selectedFile5=new File(selectList.get(4).getCompressPath());
            selectedFile6=new File(selectList.get(5).getCompressPath());
            selectedFile7=new File(selectList.get(6).getCompressPath());
            selectedFile8=new File(selectList.get(7).getCompressPath());
            selectedFile9=new File(selectList.get(8).getCompressPath());
        }
        adapter.setList(selectList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case ADDDIARY:
                return userAction.addDiary(diaryType+"",processId+"",null,Remark.getText().toString(), selectedFile1,selectedFile2,selectedFile3,selectedFile4,selectedFile5,selectedFile6,selectedFile7,selectedFile8,selectedFile9);
            case ADDDIARYFORPROGRESS:
                return userAction.addDiary(diaryType+"",null,progressId+"",Remark.getText().toString(), selectedFile1,selectedFile2,selectedFile3,selectedFile4,selectedFile5,selectedFile6,selectedFile7,selectedFile8,selectedFile9);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(activity);
        CommonResponse commonResponse = (CommonResponse) result;
        if(commonResponse.getState() == Const.SUCCESS){
            DialogWithYesOrNoUtils.getInstance().showDialog(context, "提交成功", new AlertDialogCallBack() {
                @Override
                public void executeEvent() {
                    DiaryDetailActivity.StartActivity(context,processId,progressId,type,0);
                    activity.finish();
                }

            });
        }
        NToast.shortToast(activity, commonResponse.getMsg());
    }

    private void onSelectList(final TextView view, int index) {
        SystemObjResponse.SysObjBean listb = systemObj.get(index);
        final List<SystemObjResponse.SysObjBean.ChildDictionariesBean> listc = listb.getChildDictionaries();
        final List<SystemObjResponse.SysObjBean.ChildDictionariesBean> listd = new ArrayList<>();//过滤后列表
        ArrayList<String> bodyRange = new ArrayList<>();
        for(SystemObjResponse.SysObjBean.ChildDictionariesBean entity:listc) {
            switch (type)
            {
                case 1:
                    if(entity.getId()==331 || entity.getId()==332)
                    {
                        bodyRange.add(entity.getName());
                        listd.add(entity);
                    }
                    break;
                case 2:
                    if(entity.getId()==333 || entity.getId()==333)
                    {
                        bodyRange.add(entity.getName());
                        listd.add(entity);
                    }
                    break;
                case 3:
                    if(entity.getId()==337)
                    {
                        bodyRange.add(entity.getName());
                        listd.add(entity);
                    }
                    break;
                case 100:
                    if(entity.getId()==334 || entity.getId()==335|| entity.getId()==336|| entity.getId()==341|| entity.getId()==342)
                    {
                        bodyRange.add(entity.getName());
                        listd.add(entity);
                    }
                    break;
                case 5:
                    if(entity.getId()==338 || entity.getId()==339 || entity.getId()==340)
                    {
                        bodyRange.add(entity.getName());
                        listd.add(entity);
                    }
                    break;

            }

        }
        final int size =bodyRange.size();
        OptionPicker picker = new OptionPicker(activity,bodyRange.toArray(new String[size]) );
        picker.setOffset(5);
        picker.setSelectedIndex(1);
        picker.setTextSize(14);
        picker.setHeight(600);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.WRAP);
        picker.setShadowColor(context.getResources().getColor(R.color.mainColor), 40);
        picker.setDividerColor(context.getResources().getColor(R.color.mainColorBg2));
        picker.setTopBackgroundColor(context.getResources().getColor(R.color.mainColor));
        picker.setCancelTextColor(context.getResources().getColor(R.color.white));
        picker.setSubmitTextColor(context.getResources().getColor(R.color.white));
        picker.setTopLineColor(context.getResources().getColor(R.color.mainColorBg2));
        picker.setCycleDisable(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                view.setText(option);
                SystemObjResponse.SysObjBean.ChildDictionariesBean bean=listd.get(position);
                diaryType =bean.getId();

            }
        });
        picker.show();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_down:
                onSelectList(txtDiaryType,15);
                break;
            case R.id.btn_new_diary:
                if(diaryType==0){
                    NToast.shortToast(context,"请选择上传日志类型!");
                return;
                }
                LoadDialog.show(context);
                if(progressId==0)
                    atm.request(ADDDIARY,this);
                else
                atm.request(ADDDIARYFORPROGRESS,this);

                break;
        }
    }
}