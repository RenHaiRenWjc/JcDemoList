package com.wjc.jcdemolist.demo.ServiceDemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.Utils.LogUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.keepAlive
 * Description: 保活，前台服务
 * JcChen on 2019/10/18 7:56
 */
public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";
    private static final int SERVICE_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        LogUtils.i(TAG, "onCreate: ");
        super.onCreate();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) { // 4.3以下
            //将service设置成前台服务，并且不显示通知栏消息
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // 4.3 --- 8.0
            //将service设置成前台服务
            startForeground(SERVICE_ID, new Notification());
            //删除通知栏消息
            startService(new Intent(this, InnerService.class));
        } else {// 8.0 以上
            //通知栏消息需要设置channel
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //NotificationManager.IMPORTANCE_MIN 通知栏消息的重要级别  最低，不让弹出
            //IMPORTANCE_MIN 前台时，在阴影区能看到，后台时 阴影区不消失，增加显示 IMPORTANCE_NONE时 一样的提示
            //IMPORTANCE_NONE app在前台没有通知显示，后台时有
            NotificationChannel channel = new NotificationChannel("channel", "xx", NotificationManager.IMPORTANCE_NONE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "channel").build();
                //将service设置成前台服务，8.x退到后台会显示通知栏消息，9.0会立刻显示通知栏消息
                startForeground(SERVICE_ID, notification);
            }
        }
    }

    private static class InnerService extends Service {
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            LogUtils.i(TAG, "onCreate: ");
            super.onCreate();
            // 让服务变前台服务
            startForeground(SERVICE_ID, new Notification());
            // 关闭自己
            stopSelf();
        }
    }
}
