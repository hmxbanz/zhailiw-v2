package com.zhailiw.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhailiw.app.Const;
import com.zhailiw.app.R;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wxpay_entry);
		api = WXAPIFactory.createWXAPI(this, null);
		api.registerApp(Const.APPID);
		api.handleIntent(getIntent(), this);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		Toast.makeText(getApplicationContext(),"onReq",Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResp(BaseResp resp) {

		int code = resp.errCode;

		Toast.makeText(getApplicationContext(),"返回码："+code+"",Toast.LENGTH_SHORT).show();

		if (code == 0){

			//显示充值成功的页面和需要的操作
		}

		if (code == -1){
			//错误

		}

		if (code == -2){

			//用户取消
		}

	}


}