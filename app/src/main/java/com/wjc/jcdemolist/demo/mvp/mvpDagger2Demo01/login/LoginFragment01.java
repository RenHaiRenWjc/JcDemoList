package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogTools;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/15 22:52
 */
public class LoginFragment01 extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {
	private static final String TAG = "LoginFragment01";
	private static final int RC_SIGN_IN = 9001;
	@BindView(R.id.name)
	EditText name;
	@BindView(R.id.pwd)
	EditText pwd;
	@BindView(R.id.bt_ok_email)
	Button btnLogin;
	//    Unbinder unbinder;
	private FirebaseAuth mAuth;
	private GoogleSignInClient mGoogleSignInClient;


	protected static LoginFragment01 newInstance() {
		return new LoginFragment01();
	}

	@Override
	protected int layoutId() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		// Configure Google Sign In
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(getString(R.string.default_web_client_id))
			.requestEmail()
			.build();
		// [END config_signin]

		mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
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

	@OnClick({R.id.bt_ok_email, R.id.bt_ok_google})
	public void onViewClicked(View view) {
		LogTools.i(TAG, "onViewClick: ");
		switch (view.getId()) {
			case R.id.bt_ok_email:
				mAuth.createUserWithEmailAndPassword(name.getText().toString(), pwd.getText().toString())
					.addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							LogTools.i(TAG, "onComplete: " + task.isSuccessful());
							if (task.isSuccessful())
								LogTools.d(TAG, "name=: " + mAuth.getCurrentUser().toString());
						}
					});

				break;
			case R.id.bt_ok_google:
				signIn();
				break;
		}
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try {
				// Google Sign In was successful, authenticate with Firebase
				GoogleSignInAccount account = task.getResult(ApiException.class);
				firebaseAuthWithGoogle(account);
			} catch (ApiException e) {
				// Google Sign In failed, update UI appropriately
			}
		}
	}

	private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
		AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
		mAuth.signInWithCredential(credential)
			.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					LogTools.i(TAG, "onComplete: " + task.isSuccessful());
					if (task.isSuccessful())
						LogTools.d(TAG, "name=: " + mAuth.getCurrentUser().toString());
				}
			});

	}

	private void signIn() {
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}
}
