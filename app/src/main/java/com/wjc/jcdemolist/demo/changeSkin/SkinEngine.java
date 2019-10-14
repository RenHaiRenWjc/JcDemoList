package com.wjc.jcdemolist.demo.changeSkin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;

/**
 * ClassName:com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2019/10/14 8:20
 */
public class SkinEngine {
    private Context mContext;
    private Resources mOutResource;

    private SkinEngine() {
    }

    private static final SkinEngine instance = new SkinEngine();

    public static SkinEngine getInstance() {
        return instance;
    }

    public void init(Context context) {
        //使用application的目的是，如果万一传进来的是Activity对象
        //那么它被静态对象instance所持有，这个Activity就无法释放了
        mContext = context.getApplicationContext();
    }

    public void load(final String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
//        PackageManager manager = mContext.getPackageManager();
//        PackageInfo info = manager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);
//        mOutPkgName = mInfo.packageName; // 外部资源包名

        AssetManager assetManager = mContext.getAssets();
//        mOutResource = new Resources(assetManager, mContext.getResources().getDisplayMetrics(),
//                mContext.getResources().getConfiguration());
    }

    public int getColor(int resId) {

//        if (mOutResource == null) {
//            return resId;
//        }
        mOutResource = mContext.getResources();
        String name = mOutResource.getResourceEntryName(resId) + "_day";
        int outResId = mOutResource.getIdentifier("color4_day", "color4_day", null);
        if (outResId == 0) {
            return resId;
        }
         return  mContext.getResources().getColor(resId);
//        return mOutResource.getColor(outResId);
    }
}
