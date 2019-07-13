package com.wjc.jcdemolist.demo.mvp.mvpDemo02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02
 * Description:
 * JcChen on 2019/7/13 9:27
 */
public class Utils {

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
