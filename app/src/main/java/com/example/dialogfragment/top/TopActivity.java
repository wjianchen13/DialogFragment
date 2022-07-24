package com.example.dialogfragment.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogfragment.R;

public class TopActivity extends AppCompatActivity {

    private Button btn_show;
    private Button btn_hide;
    private LinearLayout ll_bottom;
    private TopDialogFragment topSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_hide = (Button) findViewById(R.id.btn_hide);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topSheetFragment == null)
                    topSheetFragment = TopDialogFragment.newInstance();
                topSheetFragment.show(getSupportFragmentManager(), TopDialogFragment.class.getSimpleName());
            }
        });
        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topSheetFragment == null) {
                    return ;
                }
                topSheetFragment.dismiss();
            }
        });
    }
}
