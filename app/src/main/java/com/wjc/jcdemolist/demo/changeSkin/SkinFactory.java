package com.wjc.jcdemolist.demo.changeSkin;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName:com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2019/10/12 0:12
 */
public class SkinFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "SkinFactory";
    private AppCompatDelegate mDelegate; //预定义一个委托类，它负责按照系统的原有逻辑来创建view
    private List<SkinView> skinCacheViewList = new ArrayList<>();// 缓存可以换肤的view

    // 从源码抄过过来的定义 --- AppCompatViewInflater类源码里面去搜索：view = createViewFromTag(context, name, attrs);
    final Object[] mConstructorArgs = new Object[2];//View的构造函数的2个"实"参对象
    static final Class<?>[] mConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    // 用映射，将view的反射构造函数存起来
    private static final HashMap<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();
    //安卓里面控件的包名，就这么3种,这个变量是为了下面代码里，反射创建类的
    static final String[] prefixs = new String[]{
            "android.widget.", "android.view.", "android.webkit."
    };

    /**
     * Factory2 接口的方法，主要重写它
     *
     * @param parent  父view
     * @param name    tag，如 TextView、android.widget.TextView
     * @param context
     * @param attrs
     * @return
     */
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 1. 获取系统创建view，加入我们东西
        View view = mDelegate.createView(parent, name, context, attrs);
        if (view == null) { // 当系统创建 view ==null时，我们来创建
            mConstructorArgs[0] = context;
            try {
                if (-1 == name.indexOf('.')) {//明不带包名，那么我们帮他加上包名
                    view = createViewByPrefix(context, name, prefixs, attrs);
                } else {
                    view = createViewByPrefix(context, name, null, attrs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 2. 收集需要换肤的View
        collectSkinView(context, attrs, view);
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }

    public void setDelegate(AppCompatDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public void changeSkin() {
        for (SkinView skinView : skinCacheViewList) {
            skinView.changeSkin();
        }
    }


    private void collectSkinView(Context context, AttributeSet attrs, View view) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Skinable);
        boolean isSupport = typedArray.getBoolean(R.styleable.Skinable_isSupport, false);
        if (isSupport) {
            final int Len = attrs.getAttributeCount();
            HashMap<String, String> attrMap = new HashMap<>();
            for (int i = 0; i < Len; i++) {
                String attrName = attrs.getAttributeName(i);// textColor
                String attrValue = attrs.getAttributeValue(i); // @color/color4
                attrMap.put(attrName, attrValue);
            }
            SkinView skinView = new SkinView();
            skinView.view = view;
            skinView.attrMap = attrMap;
            skinView.context = context;
            skinCacheViewList.add(skinView);
        }
    }

    static class SkinView {
        View view;
        HashMap<String, String> attrMap;  // view 的属性
        Context context;

        public void changeSkin() {
            if (!TextUtils.isEmpty(attrMap.get("background"))) { //属性名,例如，这个background，text，textColor...
                int bgId = Integer.parseInt(attrMap.get("background").substring(1)); // //属性值，R.id.XXX ，int类型
                String attrType = view.getResources().getResourceTypeName(bgId);
                if (TextUtils.equals(attrType, "drawable")) {
                    view.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background));
                } else if (TextUtils.equals(attrType, "color")) {
                    view.setBackgroundColor(context.getResources().getColor(R.color.color5_day));
                }
            }
            if (view instanceof TextView) {
                String color = attrMap.get("textColor");
                if (!TextUtils.isEmpty(color)) {
                    int textColorId = Integer.parseInt(color.substring(1)); // substring 提取字符串中介于两个指定下标之间的字符
                    ((TextView) view).setTextColor(SkinEngine.getInstance().getColor(textColorId));
                    ((TextView) view).setText("testsssssssssssssss");
                }
            }
            // TODO: 2019/10/12  如果是自定义 view呢
            //那么这样一个对象，要换肤，就要写针对性的方法了，每一个控件需要用什么样的方式去换，尤其是那种，自定义的属性，怎么去set，
            // 这就对开发人员要求比较高了，而且这个换肤接口还要暴露给 自定义View的开发人员,他们去定义
            // ....
        }

    }

    /**
     * 反射创建 view
     *
     * @param context
     * @param name    tag，如TextView
     * @param prefix
     * @param attrs
     * @return
     */
    private final View createViewByPrefix(Context context, String name,
                                          String[] prefix, AttributeSet attrs) {
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        Class<? extends View> clazz = null;
        if (constructor == null) {
            try {
                if (prefix != null && prefix.length > 0) {
                    for (String p : prefix) {
                        clazz = context.getClassLoader().loadClass(p != null ? (p + name) : name).asSubclass(View.class);
                        if (clazz != null) break;
                    }
                } else if (clazz == null) {
                    clazz = context.getClassLoader().loadClass(name).asSubclass(View.class);
                }
                if (clazz == null) return null;
                constructor = clazz.getConstructor(mConstructorSignature);// 构造方法

            } catch (Exception e) {
                e.printStackTrace();
            }
            constructor.setAccessible(true);
            sConstructorMap.put(name, constructor);// 把构造方法缓存起来，方便下次再用，从内存中拿出来
        }
        Object[] args = mConstructorArgs;
        args[1] = attrs;
        try {
            final View view = constructor.newInstance(args); // 反射创建View
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
