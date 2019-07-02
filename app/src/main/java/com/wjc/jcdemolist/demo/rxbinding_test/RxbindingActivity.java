package com.wjc.jcdemolist.demo.rxbinding_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RxbindingActivity extends AppCompatActivity {
    private static final String TAG = "RxbindingActivity";
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.et2)
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbinding);
        ButterKnife.bind(this);
        test01();
    }

    @OnClick({R.id.bt1, R.id.et2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1://防抖 1s
                RxView.clicks(bt1).throttleFirst(1, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(v -> LogUtils.i(TAG, "onViewClicked: "));
                break;
        }
    }

    //频繁请求搜索提示词，1s请求一次
    public void test01() {
        RxTextView.textChanges(et2).debounce(1, TimeUnit.SECONDS).skip(1)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        LogUtils.i(TAG, "onNext: charSequence=" + charSequence);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
