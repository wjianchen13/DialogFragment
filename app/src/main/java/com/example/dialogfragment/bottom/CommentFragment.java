package com.example.dialogfragment.bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogfragment.R;


/**
 * Created by Administrator on 2018/4/18.
 */

public class CommentFragment extends BaseBottomSheetFrag implements View.OnClickListener{

    private CoordinatorLayout ll_bottom_sheet;
    private int mWidth;
    private int mHeight;
    private ImageView imgvClose;

    public static CommentFragment getInstance(int width, int height) {
        Bundle args = new Bundle();
        args.putInt("width", width);
        args.putInt("height", height);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
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
    public int getLayoutResId() {
        return R.layout.fragment_bottom_sheet;
    }

    @Override
    public void initView() {
        ll_bottom_sheet = (CoordinatorLayout) rootView.findViewById(R.id.ll_bottom_sheet);
        imgvClose = (ImageView) rootView.findViewById(R.id.imgv_close);
        imgvClose.setOnClickListener(this);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(mWidth, mHeight);
//        BottomSheetBehavior bottomSheetBehavior = new BottomSheetBehavior();
////        bottomSheetBehavior.setPeekHeight(height);
////        bottomSheetBehavior.setHideable(true);
////        layoutParams.setBehavior(bottomSheetBehavior);
//        rv_test.setLayoutParams(layoutParams);
//        swipeDismissBehavior = new SwipeDismissBehavior();
//        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
//
//        swipeDismissBehavior.setDragDismissDistance(0.5F);
//        swipeDismissBehavior.setStartAlphaSwipeDistance(0F);
//        swipeDismissBehavior.setEndAlphaSwipeDistance(0.5F);
//        swipeDismissBehavior.setSensitivity(0);
//        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
//        layoutParams.setBehavior(swipeDismissBehavior);
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
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_content, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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

//    private CoordinatorLayout ll_bottom_sheet;
//    private static LinearLayout mll_bottom;
//    private BottomSheetBehavior mBehavior;
////    private SwipeDismissBehavior swipeDismissBehavior;
//    private static Context mContext;
//
//    public static CommentFragment getInstance(Context context, LinearLayout ll_bottom) {
//        mContext = context;
//        mll_bottom = ll_bottom;
//        CommentFragment fragment = new CommentFragment();
//
//        return fragment;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.fragment_bottom_sheet;
//    }
//
//    @Override
//    public void initView() {
//        ll_bottom_sheet = (CoordinatorLayout) rootView.findViewById(R.id.ll_bottom_sheet);
//        RecyclerView rv_test = (RecyclerView) rootView.findViewById(R.id.rv_test);
//        int width = mll_bottom.getWidth();
//        int height = mll_bottom.getHeight();
//        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(width, height);

//        ll_bottom_sheet.setLayoutParams(layoutParams);
//        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_test);
//        new ListAdapter(mRecyclerView);
//    }
//
//    private final class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
//
//        public ListAdapter(RecyclerView recyclerView) {
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            recyclerView.setAdapter(this);
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_bottom, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.textView.setText("item" + (++position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return 50;
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//
//            public final TextView textView;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                textView = (TextView) itemView.findViewById(R.id.textview);
//            }
//        }
//    }
}
