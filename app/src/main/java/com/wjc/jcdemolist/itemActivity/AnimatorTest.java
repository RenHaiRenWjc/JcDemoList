package com.wjc.jcdemolist.itemActivity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

public class AnimatorTest extends AppCompatActivity {
    private static final String TAG = "AnimatorTest";
    private View target;
    private TextView target02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_test);
        target = findViewById(R.id.iv_test);
        target02 = findViewById(R.id.tv_test02);
        ValueAnimatorTest();
        ofObjectValueAnimatorTest();

        target.animate().rotation(300).alpha(0.5f).setDuration(1000);

    }

    private void ofObjectValueAnimatorTest() {


        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<Character>() {
            @Override
            public Character evaluate(float fraction, Character startValue, Character endValue) {
                int start = (int) startValue;
                int end = (int) endValue;
                int value = (int) (start + fraction * (end - start));
                return (char) value;
            }
        }, Character.valueOf('A'), new Character('Z'));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char) animation.getAnimatedValue();
                LogUtils.i(TAG, "onAnimationUpdate: text=" + text + ",animation.getAnimatedValue()=" + animation.getAnimatedValue());
                target02.setText(String.valueOf(text));
            }
        });
        valueAnimator.setDuration(26000);
        valueAnimator.setRepeatCount(ValueAnimator.RESTART);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.start();
    }


    /**
     * 1.  ValueAnimator 只负责对指定的数字区间进行动画运算
     * 2.  我们需要对运算过程进行监听，然后自己对控件做动画操作
     */
    private void ValueAnimatorTest() {
        ValueAnimator animator = ValueAnimator.ofInt(10, 200); // 值从 1 到20 的动画，时长为1s，不断重复，从头开始
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.RESTART);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {// 监听值的回调
                int value = (int) animation.getAnimatedValue();
                LogUtils.i(TAG, "onAnimationUpdate: animation=" + value);
                target.layout(target.getLeft(), value, target.getRight(), value + target.getHeight());
            }
        });

        animator.setEvaluator(new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return (int) (startValue + fraction * (endValue - startValue));
            }
        });

        animator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 1 - input;
            }
        }); // 加速器
        animator.start();
    }
}
