package com.wjc.jcdemolist.demo.jetpack.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogTools;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {
	private static final String TAG = "WelcomeFragment";
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private Unbinder mUnbinder;

	public WelcomeFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment WelcomeFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static WelcomeFragment newInstance(String param1, String param2) {
		WelcomeFragment fragment = new WelcomeFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View mRootView = inflater.inflate(R.layout.fragment_welcome, container, false);
		mUnbinder = ButterKnife.bind(this, mRootView);
		return mRootView;
	}

	@OnClick({R.id.bt_register, R.id.bt_login})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.bt_register:
				LogTools.i(TAG, ":bt_register ");
				NavHostFragment.findNavController(this)
					.navigate(R.id.action_welcomeFragment_to_registerFragment);
				break;
			case R.id.bt_login:
				LogTools.i(TAG, ": bt_login");
				NavHostFragment.findNavController(this)
					.navigate(R.id.action_welcomeFragment_to_jcLoginFragment);
				break;
		}
	}
}