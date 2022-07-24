package com.example.dialogfragment.top;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.dialogfragment.R;

/**
 * 顶部弹出
 */
public class TopDialogFragment extends BaseDialogFragment implements View.OnClickListener{


    public static TopDialogFragment newInstance() {
        Bundle args = new Bundle();
        TopDialogFragment fragment = new TopDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View _onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedEInstanceState) {
        View v = inflater.inflate(R.layout.dialog_top_in, container, false);
        return v;
    }

    protected boolean isCanceledOnTouchOutside() {
        return true;
    }

    protected float getDimAmount() {
        return 0.0f;
    }

    protected boolean isNotFocusable() {
        return true;
    }

    /**
     *
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = getDimAmount();
            params.windowAnimations = R.style.TopDialogFragment;

            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setGravity(Gravity.TOP);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setAttributes(params);
        }

    }

    @Override
    public void onClick(View v) {
    }

}
