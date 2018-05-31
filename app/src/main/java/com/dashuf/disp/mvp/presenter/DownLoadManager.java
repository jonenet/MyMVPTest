package com.dashuf.disp.mvp.presenter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

public class DownLoadManager {


    public void downLoad(Context context) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        String apkUrl = "https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";

        DownloadManager.Request request = new
                DownloadManager.Request(Uri.parse(apkUrl));

        request.setDestinationInExternalPublicDir("dirType", "/mydownload/QQ.apk");

        // request.setTitle("TX QQ");

        // request.setDescription("This is TX QQ");

        // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);

        //request.setMimeType("application/cn.trinea.download.file");

        long downloadId = downloadManager.enqueue(request);

        request.setMimeType("application/cn.trinea.download.file");

        downloadManager.getUriForDownloadedFile(downloadId);


    }


}
