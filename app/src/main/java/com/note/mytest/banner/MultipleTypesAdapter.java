package com.note.mytest.banner;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.note.mytest.R;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局,多个不同UI切换
 */
public class MultipleTypesAdapter extends BannerAdapter<DataBean, RecyclerView.ViewHolder> {
    private Context context;

    public MultipleTypesAdapter(Context context, List<DataBean> mDatas) {
        super(mDatas);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
            case 2:
//                return new VideoHolder(BannerUtils.getView(parent, R.layout.banner_video));
            case 3:
                return new TitleHolder(BannerUtils.getView(parent, R.layout.banner_title));
        }
        return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
    }

    @Override
    public int getItemViewType(int position) {
        return getData(getRealPosition(position)).viewType;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, DataBean data, int position, int size) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 1:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.imageView.setImageResource(data.imageRes);
                break;
            case 2:
                break;
            case 3:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.title.setText(data.title);
                titleHolder.title.setBackgroundColor(Color.parseColor(DataBean.getRandColor()));
                break;
        }
    }


}
