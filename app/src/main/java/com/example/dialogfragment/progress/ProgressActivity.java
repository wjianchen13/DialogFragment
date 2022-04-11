package com.example.dialogfragment.progress;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogfragment.R;

public class ProgressActivity extends AppCompatActivity {

    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }

    /**
     * 主要逻辑就在这里，这里先显示dialogfragment,初始化进度是80,之后设置进度是100，然后隐藏dialogfragment
     * 之后初始化进度为0，之再显示dialogfragment，这个进度始终是最后dismiss保存的进度值100
     *
     */
    public void onShow(View v) {
        final TestingDialogFragment testingDialogFragment = TestingDialogFragment.newInstance("test", 80);
        testingDialogFragment.show(getSupportFragmentManager(),"");

        final Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                p = (p + 1) % 5;
                if(p == 1) {
                    testingDialogFragment.setProgress(100);
                    mHandler.postDelayed(this, 2000);
                }
                else if(p == 2) {
                    testingDialogFragment.dismissAllowingStateLoss();
                    mHandler.postDelayed(this, 2000);
                } else if(p == 3){
                    testingDialogFragment.resetProgress();
                    testingDialogFragment.show(getSupportFragmentManager(), "TestingDialogFragment");
                    mHandler.postDelayed(this, 2000);
                } else if(p == 4){
                    testingDialogFragment.setProgress(20);
                    mHandler.postDelayed(this, 2000);
                } else {
                    testingDialogFragment.setProgress(40);
                }

            }
        }, 2000);
    }
}
