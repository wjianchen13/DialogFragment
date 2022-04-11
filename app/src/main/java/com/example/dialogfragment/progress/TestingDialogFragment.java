package com.example.dialogfragment.progress;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dialogfragment.R;

/**
 * name: TestingDialogFragment
 * desc: 测试中dialog
 * author:
 * date: 2019-10-22 16:00
 * remark:
 */
public class TestingDialogFragment extends DialogFragment implements View.OnClickListener{

    String content;

    private View mView;

    int progress;

    protected TextView tv_tip;

    protected ProgressBar pb;


    protected String content1;
    protected int progress1;

    public static TestingDialogFragment newInstance(String tip, int progress) {
        Bundle args = new Bundle();
        args.putString("title", tip);
        args.putInt("progress", progress);
        TestingDialogFragment fragment = new TestingDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
        }
        mView = inflater.inflate(R.layout.dialog_progress, container, false);
        tv_tip = mView.findViewById(R.id.tv_tip);
        pb = mView.findViewById(R.id.pb);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try{
            Context context = getDialog().getContext();
            int divierId = context.getResources().getIdentifier("android:id/titleDivider", null, null);
            if(divierId != 0) {
                View divider = getDialog().findViewById(divierId);
                if(divider != null)
                    divider.setBackgroundColor(Color.TRANSPARENT);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        Bundle bundle = getArguments();
        if(bundle != null) {
            System.out.println("===============> progress: " + bundle.getInt("progress"));
            if(pb != null)
                pb.setProgress(bundle.getInt("progress"));
            if(tv_tip != null)
                tv_tip.setText(bundle.getString("title"));
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(pb != null)
//            pb.setProgress(progress1);
//        if(tv_tip != null)
//            tv_tip.setText(content1);
    }

    public void resetProgress(){
        setProgress(40);
        setContent("test1");
    }

    public void setProgress(int progress) {
        if(pb != null && isVisible())
            pb.setProgress(progress);
        else {
            Bundle bundle = getArguments();
            if(bundle != null)
                bundle.putInt("progress", progress);
        }
    }

    public void setContent(String content){
        if(tv_tip != null && isVisible())
            tv_tip.setText(content);
        else {
            Bundle bundle = getArguments();
            if(bundle != null)
                bundle.putString("title", content);
        }
    }

}
