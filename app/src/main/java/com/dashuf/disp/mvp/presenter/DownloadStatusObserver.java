package com.dashuf.disp.mvp.presenter;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
//https://www.jianshu.com/p/7ad92b3d9069
//https://www.jianshu.com/p/b57628947aec
public class DownloadStatusObserver extends ContentObserver {
    private Context context;
    private long downloadId;

    public DownloadStatusObserver(Context context, Handler handler, long downloadId) {
        super(handler);
        this.context = context;
        this.downloadId = downloadId;
    }

    @Override
    public void onChange(boolean selfChange) {
        int[] bytesAndStatus = getBytesAndStatus(context, downloadId);
        int currentSize = bytesAndStatus[0];//当前大小
        int totalSize = bytesAndStatus[1];//总大小
        int status = bytesAndStatus[2];//下载状态

    }


    public int[] getBytesAndStatus(Context context, long downloadId) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        int[] bytesAndStatus = new int[]{-1, -1, 0};
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = null;
        try {
            assert downloadManager != null;
            c = downloadManager.query(query);
            if (c != null && c.moveToFirst()) {
                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return bytesAndStatus;
    }

}

