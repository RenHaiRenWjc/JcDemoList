package com.wjc.jcdemolist.demo.rxbinding_test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Unit;

public class RxbindingActivity extends AppCompatActivity {
    private static final String TAG = "RxbindingActivity";
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3_tel)
    EditText et3Tel;
    @BindView(R.id.et4_pwd)
    EditText et4Pwd;
    @BindView(R.id.bt_ok)
    Button btOk;
    @BindView(R.id.bt2)
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbinding);
        ButterKnife.bind(this);
        test01();
        test02();
        test03_01();
        test04();

    }

    @OnClick({R.id.bt1, R.id.et2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1://防抖 1s
                RxView.clicks(bt1).throttleFirst(1, TimeUnit.SECONDS)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(v -> LogUtils.i(TAG, "onViewClicked: "));
                break;
        }
    }

    public void test04() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        })
                .flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 10; i < 14; i++) {
                    list.add(integer + "_concatMap_" + i);
                    // 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        })
//                .concatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 10; i < 14; i++) {
//                    list.add(integer + "_concatMap_" + i);
//                    // 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
//                    // 最终合并，再发送给被观察者
//                }
//                return Observable.fromIterable(list);
//            }
//        })
                .subscribe(o -> {
                    LogUtils.i(TAG, "test04---: o=" + o);
                });
    }

    public void test03() {
        RxView.clicks(bt2).throttleFirst(20, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit aBoolean) throws Exception {
                        LogUtils.i(TAG, "accept: Thread=" + Thread.currentThread());
                        bt2.setEnabled(false);
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Observable.interval(1, TimeUnit.SECONDS)
                            .take(20) // the maximum number of items to emit
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Long aLong) {
                                    LogUtils.i(TAG, "test03:--- thread=" + Thread.currentThread());
                                    bt2.setText("剩余" + (20 - aLong) + " s");
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {
                                    LogUtils.i(TAG, "test03:--- thread--=" + Thread.currentThread());
                                    bt2.setText("获取验证码");
                                    bt2.setEnabled(true);
                                }
                            });
                });
    }

    //获取验证码
    public void test03_01() {
        RxView.clicks(bt2).throttleFirst(20, TimeUnit.SECONDS)
                .doOnNext(v -> {
                    bt2.setEnabled(false);
                }).subscribe(s -> {
            Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).take(20)
                    .subscribe(aLong -> {
                        bt2.setText("剩下" + (20 - aLong) + "s");
                    }, Throwable::printStackTrace, () -> {
                        bt2.setText("获取验证码");
                        bt2.setEnabled(true);
                    });
        });
    }

    //频繁请求搜索提示词，1s请求一次  ==> debounce/switchMap 联想搜索
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

    //账号、密码、登录
    public void test02() {
        Observable<CharSequence> observableName = RxTextView.textChanges(et3Tel);
        Observable<CharSequence> observablePwd = RxTextView.textChanges(et4Pwd);
        Observable.combineLatest(observableName, observablePwd, (tel, pwd) -> isPhoneValid(tel.toString())
                && isPasswordValid(pwd.toString()))
                .subscribe(btOk::setEnabled);

        RxView.clicks(btOk).throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> Toast.makeText(RxbindingActivity.this, "登录成功", Toast.LENGTH_SHORT).show());
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}
