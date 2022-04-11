package com.example.dialogfragment;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/10/18.
 */

public class MyRelativeLayout extends RelativeLayout {

    public MyRelativeLayout(Context context) {
        super(context);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
//        setClickable(true);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

//    @Override
//    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
//        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }
}
