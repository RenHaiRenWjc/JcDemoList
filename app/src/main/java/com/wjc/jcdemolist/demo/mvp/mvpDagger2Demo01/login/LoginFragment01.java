package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;


import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogTools;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/15 22:52
 */
public class LoginFragment01 extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {
	private static final String TAG = "LoginFragment01";
	@BindView(R.id.name)
	EditText name;
	@BindView(R.id.pwd)
	EditText pwd;
	@BindView(R.id.bt_ok_test01)
	Button btnLogin;
	//    Unbinder unbinder;
	private FirebaseAuth mAuth;


	protected static LoginFragment01 newInstance() {
		return new LoginFragment01();
	}

	@Override
	protected int layoutId() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		mAuth = FirebaseAuth.getInstance();

	}

	@Override
	public void onResume() {
		super.onResume();
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if (currentUser == null) {//没有登录

		} else {
//			name.setText(currentUser.getPhoneNumber());
//			pwd.setText(currentUser.getDisplayName());
		}

	}

	private static boolean isUsrValid(String usr) {
		return true;
	}

	private static boolean isPasswordValid(String pwd) {
		return true;
	}


	@Override
	public void loginSuccess(LoginContract.Model result) {
		LogTools.i(TAG, "loginSuccess: result=" + result.getMsg() + ", is " + result.isSuccess());
	}

	@Override
	public void setPresenter(LoginContract.Presenter presenter) {
		if (mPresenter == null) {
			mPresenter = presenter;
		}
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
//        unbinder.unbind();
	}

	@OnClick(R.id.bt_ok_test01)
	public void onViewClicked() {
		LogTools.i(TAG, "onViewClick: ");
		mAuth.createUserWithEmailAndPassword(name.getText().toString(), pwd.getText().toString())
			.addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					LogTools.i(TAG, "onComplete: " + task.isSuccessful());
					if (task.isSuccessful())
						LogTools.d(TAG, "name=: " + mAuth.getCurrentUser().toString());
				}
			});
		
//		Observable<CharSequence> ObservableName = RxTextView.textChanges(name);
//		Observable<CharSequence> ObservablePassword = RxTextView.textChanges(pwd);
//
//		Observable.combineLatest(ObservableName, ObservablePassword
//			, (phone, password) -> isUsrValid(phone.toString()) && isPasswordValid(password.toString()))
//			.subscribe(btnLogin::setEnabled);
//		RxView.clicks(btnLogin)
//			.throttleFirst(1, TimeUnit.SECONDS)
//			.subscribeOn(AndroidSchedulers.mainThread())
//			.subscribe(v -> {
//				String username = name.getText().toString();
//				String password = pwd.getText().toString();
//				mPresenter.login(username, password);
//			});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		FirebaseAuth.getInstance().signOut();
	}
}
