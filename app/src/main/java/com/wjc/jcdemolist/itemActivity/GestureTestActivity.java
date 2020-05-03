package com.wjc.jcdemolist.itemActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.wjc.jcdemolist.R;

/**
 * https://blog.csdn.net/harvic880925/article/details/39520901
 */
public class GestureTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_test);
        GestureDetector gestureDetector = new GestureDetector(this, listener);
        gestureDetector.setOnDoubleTapListener(onDoubleTapListener);
    }

    GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {// 按下
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {// 按下时间超过瞬间，并且没有松开或拖动的

        }

        /**
         *   轻击一下屏幕，立刻抬起来
         *   点击一下非常快（不滑动）  onDown ---> onSingleTapUp ---> onSingleTapConfirmed
         *   点击一下稍微慢点的（不滑动）onDown ---> onShowPress ---> onSingleTapUp --->onSingleTapConfirmed
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;

        }

        /**
         * 拖动事件，在 move 动作发生时就会触发
         * 滑屏(手指触动到屏幕后，稍微滑动后马上松开)、拖动
         * onDown --->onScroll --->onScroll .... ---> onFling
         * @param e1
         * @param e2
         * @param distanceX
         * @param distanceY
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        /**
         * 长按屏幕，并且超过一定时间
         * onDown ---> onShowPress ---> onLongPress
         * @param e
         */
        @Override
        public void onLongPress(MotionEvent e) {

        }


        /**
         * 滑屏，按下屏幕、快速移动后松开
         * @param e1 第一个 ACTION_DOWN
         * @param e2 最后一个 ACTION_MOVE
         * @param velocityX X轴上的移动速度，像素/秒
         * @param velocityY Y轴上的移动速度，像素/秒
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;

        }
    };

    GestureDetector.OnDoubleTapListener onDoubleTapListener = new GestureDetector.OnDoubleTapListener() {

        /**
         * 单击事件
         * 判定该次点击是SingleTap 还是 DoubleTap，如果连续点击两次就是DoubleTap手势，如果只点击一次，
         * 系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap，然后触发SingleTapConfirmed事件
         * OnDown->OnSingleTapUp->OnSingleTapConfirmed
         * 与 onSingleTapUp 区别：
         * onSingleTapUp，只要手抬起就会执行，而对于onSingleTapConfirmed来说，如果双击的话，则onSingleTapConfirmed不会执行
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        /**
         *  双击事件
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        /**
         * 双击间隔中发生的动作：指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件
         * 0：ACTION_DOWN，1：ACTION_UP，2：ACTION_MOVE
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    };
}
