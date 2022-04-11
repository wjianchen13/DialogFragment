package com.example.dialogfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class DialogFragemtActivity extends Activity {

	private Button btn_test;
	private EditText edtv1;
	private EditText edtv2;
	private Handler mHandler;
	private InputMethodManager imm1;
	private InputMethodManager imm2;
	private IBinder binder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_fragment);
		edtv1 = (EditText)findViewById(R.id.tv_test1);
		btn_test = (Button) findViewById(R.id.btn_test);
		edtv2 = (EditText)findViewById(R.id.tv_test2);
		imm1 = (InputMethodManager) edtv1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm2 = (InputMethodManager) edtv1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

			}
		}, 200);
	}

//	//显示输入法
//	public void showInputMeth() {
//		if (edtv1 != null) {
//			edtv1.setFocusable(true);
//			edtv1.requestFocus();
//			imm1.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
//		}
//	}

	//显示输入法
	public void showInputMeth() {
		binder = edtv2.getWindowToken();
		if (edtv1 != null) {
			edtv1.setFocusable(true);
			edtv1.requestFocus();

			imm1.showSoftInput(edtv1, InputMethodManager.SHOW_FORCED);
		}
	}

	//隐藏输入法
	private void hideInputMeth() {
		if (edtv2 != null) {
			imm2.hideSoftInputFromWindow(btn_test.getWindowToken(), 0);
		}
	}

	public void onShowKeyboard(View view) {
//		binder = edtv2
		showInputMeth();
	}

	public void onHideKeyboard(View view) {
		hideInputMeth();
	}

	public void onHideEdittext(View view) {
		edtv1.setVisibility(View.GONE);
	}

	public void onShow(View view) {
		EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
		editNameDialog.show(getFragmentManager(), "EditNameDialog");
	}

	public void onKill(View view) {
		((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
