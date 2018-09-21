package com.zhailiw.app.widget.downloadService;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.zhailiw.app.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 用来下载app的服务
 */
public class DownloadService extends IntentService {
    private static final int BUFFER_SIZE = 10 * 1024; // 8k ~ 32K
    private static final String TAG = "DownloadService";
    private NotificationManager mNotifyManager;
    private Builder mBuilder;
    private File apkFile;
    private LocalBroadcastManager lbm;
    private BroadcastReceiver receiver;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lbm = LocalBroadcastManager.getInstance(this);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("com.notification.click")){
                    Intent installAPKIntent = new Intent(Intent.ACTION_VIEW);
                    installAPKIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//安装完成后打开新的apk
                    installAPKIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    installAPKIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                    DownloadService.this.startActivity(installAPKIntent);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }

            }
        };
        lbm.registerReceiver(receiver, new IntentFilter("com.notification.click"));

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Builder(this);

        String appName = getString(getApplicationInfo().labelRes);
        int icon = getApplicationInfo().icon;

        mBuilder.setContentTitle(appName).setSmallIcon(icon);

        //开始下载新的apk
        String urlStr = intent.getStringExtra("url");
        InputStream in = null;
        FileOutputStream out = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

            urlConnection.connect();
            long bytetotal = urlConnection.getContentLength();
            long bytesum = 0;
            int byteread = 0;
            in = urlConnection.getInputStream();
            File dir = StorageUtils.getCacheDirectory(this);
            String apkName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.length());
            apkFile = new File(dir, apkName);
            out = new FileOutputStream(apkFile);
            byte[] buffer = new byte[BUFFER_SIZE];

            int oldProgress = 0;

            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread);

                int progress = (int) (bytesum * 100L / bytetotal);
                // 如果进度与之前进度相等，则不更新，如果更新太频繁，否则会造成界面卡顿
                if (progress != oldProgress) {
                    updateProgress(progress);
                }
                oldProgress = progress;
            }
            //下载完成
            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
            mBuilder.setContentText(getString(R.string.download_success)).setProgress(0, 0, false);
            mNotifyManager.notify(0, mBuilder.build());
            mNotifyManager.cancelAll();

            //下载完成后直接安装打开
            Intent installAPKIntent = new Intent(Intent.ACTION_VIEW);
            installAPKIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//安装完成后打开新的apk
            installAPKIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
                Uri fileUri = FileProvider.getUriForFile(DownloadService.this,"com.zhailiw.app.provider",apkFile);

                installAPKIntent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            }else{
                installAPKIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            }

            this.startActivity(installAPKIntent);
            android.os.Process.killProcess(android.os.Process.myPid());


        } catch (Exception e) {
            Log.e(TAG, "download apk file error", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    private void updateProgress(int progress) {
        //"正在下载:" + progress + "%"
        mBuilder.setContentText(this.getString(R.string.download_progress, progress)).setProgress(100, progress, false);

        //setContentInent如果不设置在4.0+上没有问题，在4.0以下会报异常
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingintent);
        mNotifyManager.notify(0, mBuilder.build());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lbm.unregisterReceiver(receiver);
    }
}