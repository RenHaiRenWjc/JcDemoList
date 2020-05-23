package com.wjc.jcdemolist.demo.changeSkin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

/**
 * ClassName:com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2019/10/14 8:20
 */
public class SkinEngine {
    private static final String TAG = "SkinEngine";
    private Context mContext;
    private Resources mOutResource;
    private String mOutPkgName;

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
        mOutResource = mContext.getResources();
    }

    public void load(final String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        PackageManager manager = mContext.getPackageManager();
        PackageInfo mInfo = manager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        mOutPkgName = mInfo.packageName; // 外部资源包名

        AssetManager assetManager = mContext.getAssets();
        Method addAssetPath = null;//为什么要反射执行这个方法？因为它是hide的,不直接对外开放，只能反射调用
        try {
            addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);//反射执行方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最终创建出一个 "外部资源包"mOutResource ,它的存在，就是要让我们的app有能力加载外部的资源文件
        mOutResource = new Resources(assetManager, mContext.getResources().getDisplayMetrics(),
                mContext.getResources().getConfiguration());
    }

    public int getColor(int resId) {
        if (mOutResource == null) {
            return resId;
        }
        String name = mOutResource.getResourceEntryName(resId) + "_day";
        int outResId = mOutResource.getIdentifier(name, "color", mContext.getPackageName());
        if (outResId == 0) {
            return resId;
        }
        return mContext.getResources().getColor(outResId);
    }
}
