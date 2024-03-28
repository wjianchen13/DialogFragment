package com.example.dialogfragment.fullscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogfragment.R;
import com.example.dialogfragment.bottom.CommentFragment;

public class FullScreenActivity extends AppCompatActivity {

    private Button btn_comment;
    private Button btn_comment1;
    private LinearLayout ll_bottom;
    private FullScreenDialogFragment fullScreenDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_comment1 = (Button) findViewById(R.id.btn_comment1);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullScreenDialogFragment == null)
                    fullScreenDialogFragment = new FullScreenDialogFragment();
                fullScreenDialogFragment.show(getSupportFragmentManager(), CommentFragment.class.getSimpleName());
            }
        });
        btn_comment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
