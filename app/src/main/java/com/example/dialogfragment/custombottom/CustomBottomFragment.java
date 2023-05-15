package com.example.dialogfragment.custombottom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogfragment.R;
import com.example.dialogfragment.bottom.CommentFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CustomBottomFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    protected Context mContext;

    protected View rootView;
    protected BottomSheetBehavior mBehavior;

    protected Dialog dialog;

    public static CustomBottomFragment getInstance(int width, int height) {
        Bundle args = new Bundle();
        args.putInt("width", width);
        args.putInt("height", height);
        CustomBottomFragment fragment = new CustomBottomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mWidth = bundle.getInt("width");
            mHeight = bundle.getInt("height");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.1f;
        window.setAttributes(windowParams);
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除缓存View和当前ViewGroup的关联
        ((ViewGroup) (rootView.getParent())).removeView(rootView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_custom_bottom_sheet, container);
        initView();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBehavior = BottomSheetBehavior.from((View) rootView.getParent());
        mBehavior.setHideable(true);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN)
                    dismiss();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        //圆角边的关键
        ((View) rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * PeekHeight默认高度256dp 会在该高度上悬浮
                 * 设置等于view的高 就不会卡住
                 */
                mBehavior.setPeekHeight(rootView.getHeight());
            }
        });
    }

    private CoordinatorLayout ll_bottom_sheet;
    private int mWidth;
    private int mHeight;
    private ImageView imgvClose;

    public void initView() {
        ll_bottom_sheet = (CoordinatorLayout) rootView.findViewById(R.id.ll_bottom_sheet);
        imgvClose = (ImageView) rootView.findViewById(R.id.imgv_close);
        imgvClose.setOnClickListener(this);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(mWidth, mHeight);
        ll_bottom_sheet.setLayoutParams(layoutParams);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_test);
        new ListAdapter(mRecyclerView);
    }

    @Override
    public void onClick(View v) {
        if(!isShowing()) {
            return ;
        }
        close(true);
    }

    private final class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        public ListAdapter(RecyclerView recyclerView) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(this);
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListAdapter.ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_content, parent, false));
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
            holder.textView.setText("item" + (++position));
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public final TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.textview);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), textView.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * 重置的View和数据的空方法 子类可以选择实现
     * 为避免多次inflate 父类缓存rootView
     * 所以不会每次打开都调用{@link #initView()}方法
     * 但是每次都会调用该方法 给子类能够重置View和数据
     */
    public void resetView() {

    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    /**
     * 使用关闭弹框 是否使用动画可选
     * 使用动画 同时切换界面Aty会卡顿 建议直接关闭
     *
     * @param isAnimation
     */
    public void close(boolean isAnimation) {
        if (isAnimation) {
            if (mBehavior != null)
                mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            dismiss();
        }
    }

}
