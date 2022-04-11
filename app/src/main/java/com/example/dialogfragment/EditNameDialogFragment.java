package com.example.dialogfragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class EditNameDialogFragment extends DialogFragment implements View.OnClickListener{

	private EditText edtv1;
	private EditText edtv2;
	private Handler mHandler;
	private InputMethodManager imm1;
	private InputMethodManager imm2;
	private IBinder binder;
	private View view;
	private RelativeLayout rlytTest;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
//	 	setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_fragment);
		super.onCreate(savedInstanceState);


	}

	@Override
	public void onStart() {
		super.onStart();
		if (getDialog() != null && getDialog().getWindow() != null) {

			Window window = getDialog().getWindow();
			WindowManager.LayoutParams params = window.getAttributes();
//			params.width = WindowManager.LayoutParams.MATCH_PARENT;
//			params.height = dip2px(getActivity(), 400);
//			params.height = WindowManager.LayoutParams.MATCH_PARENT;
			window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
			window.setAttributes(params);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (getResources().getBoolean(R.bool.large_layout)) {
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		getDialog().setCanceledOnTouchOutside(true);
//		getDialog().setOnCancelListener(new DialogInterface.OnCancelListener() {
//			@Override
//			public void onCancel(DialogInterface dialog) {
//				int a = 0;
//				Toast.makeText(getActivity(), "" + a, Toast.LENGTH_SHORT).show();
//			}
//		});
		view = inflater.inflate(R.layout.fragment_edit_name, container, false);
//		rlytTest = (RelativeLayout)view.findViewById(R.id.rlyt_test);
//		rlytTest.setOnClickListener(this);
		edtv1 = (EditText)view.findViewById(R.id.id_txt_your_name_1);
		edtv2 = (EditText)view.findViewById(R.id.id_txt_your_name);
		imm1 = (InputMethodManager) edtv1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm2 = (InputMethodManager) edtv1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		// 加了这句软件盘会自动弹出
//		getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				showInputMeth();
			}
		}, 200);

//		getDialog().getWindow().getDecorView()
//				.setOnTouchListener(new View.OnTouchListener() {
//					@Override
//					public boolean onTouch(View view, MotionEvent event) {
//
//						// 这里可以实现点击对话框上除了EditText区域外的地方收起软键盘，点击对话框外退出对话框
//						if (event.getAction() == MotionEvent.ACTION_DOWN) {
//						boolean isTouch = isDownView(view, event.getRawX(),event.getRawY());
//						System.out.println("===============================> isTouch: " + isTouch);
//
//						InputMethodManager manager = (InputMethodManager)getActivity()
//								.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//							if (getDialog().getCurrentFocus() != null
//									&& getDialog().getCurrentFocus().getWindowToken() != null) {
//								manager.hideSoftInputFromWindow(
//										getDialog().getCurrentFocus().getWindowToken(),
//										0);
//							}
//							if(!isTouch)
//								dismiss();
//						}
//
//						return false;
//					}
//				});
		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.rlyt_test:
				int C = 0;
				break;
		}
	}

	//显示输入法
	public void showInputMeth() {
		binder = edtv1.getWindowToken();
		if (edtv1 != null) {
			edtv1.setFocusable(true);
			edtv1.requestFocus();
			imm1.showSoftInput(edtv1, InputMethodManager.SHOW_FORCED);
		}
	}

	//隐藏输入法
	private void hideInputMeth() {
		if (binder != null) {
			imm1.hideSoftInputFromWindow(binder, 0);
		}
	}


	public static int dip2px(Context context, float dpValue) {
		return (int) (dpValue * getDensity(context) + 0.5f);
	}

	public static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	public EditNameDialogFragment() {
		super();
	}

	@Override
	public void setStyle(int style, int theme) {
		super.setStyle(style, theme);
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
//		dismiss();
//		hideInputMeth();
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	/**
	 * 是否隐藏软键盘
	 * @param
	 * @return
	 */
	public boolean isShouldHideSoftInput(View view, float x, float y) {
		boolean shouldHide = true;
		if (isDownView(view, x, y)) {
			if (view instanceof ViewGroup) {
				ViewGroup viewGroup = (ViewGroup) view;
				for (int i = 0; i < viewGroup.getChildCount(); i++) {
					shouldHide = isShouldHideSoftInput(viewGroup.getChildAt(i), x, y);
					if (!shouldHide) {
						break;
					}
				}
			} else {
				if (view instanceof EditText || view instanceof AppCompatEditText) {
					shouldHide = false;
				}
			}
		}
		Log.i("message:",shouldHide+"");
		return shouldHide;
	}

	/**
	 * 判断点击位置和View的关系
	 * @param
	 * @return
	 */
	public boolean isDownView(View view, float x, float y) {
		boolean thisView = false;
		int[] location = new int[2];
		view.getLocationOnScreen(location);
		int xl = location[0];
		int yl = location[1];
		if (x > xl && x < xl + view.getWidth() && y > yl && y < yl + view.getHeight()) {
			thisView = true;
		}
		return thisView;
	}
}
