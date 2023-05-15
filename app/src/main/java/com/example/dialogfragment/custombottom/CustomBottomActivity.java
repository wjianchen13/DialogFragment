package com.example.dialogfragment.custombottom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogfragment.R;
import com.example.dialogfragment.bottom.CommentFragment;

public class CustomBottomActivity extends AppCompatActivity {

    private Button btn_comment;
    private Button btn_comment1;
    private LinearLayout ll_bottom;
    private CustomBottomFragment bottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bottom);
        btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_comment1 = (Button) findViewById(R.id.btn_comment1);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetFragment == null)
                    bottomSheetFragment = CustomBottomFragment.getInstance(ll_bottom.getWidth(), ll_bottom.getHeight());
                bottomSheetFragment.show(getSupportFragmentManager(), CommentFragment.class.getSimpleName());
            }
        });
        btn_comment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetFragment == null) {
                    return ;
                }
                bottomSheetFragment.close(true);
            }
        });
    }
}
