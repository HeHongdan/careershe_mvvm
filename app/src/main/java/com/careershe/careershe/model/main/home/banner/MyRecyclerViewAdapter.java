package com.careershe.careershe.model.main.home.banner;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.careershe.careershe.R;
import com.careershe.ui.screenmatch.utils.ConvertUtils;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.listener.OnBannerListener;

import static com.careershe.basics.base.BaseApp.getContext;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/8
 * @since v2021/3/8
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.banner_item_rv) {
            return new MyBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item_rv, parent, false));
        } else {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_c_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyBannerViewHolder) {
            Banner banner = ((MyBannerViewHolder) holder).banner;
            banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
//            banner.setBannerRound(ConvertUtils.dp2px(getContext(), 4));
//            banner.setIndicator(new RoundLinesIndicator(context));
            banner.setIndicator(new RectangleIndicator(context));
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(Object data, int position) {
                    Toast.makeText(getContext(), "点击的下标= " + position, Toast.LENGTH_SHORT).show();
                }
            });
//            banner.setIndicatorSelectedWidth((int) ConvertUtils.dp2px(getContext(), 4));
        } else if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).cardView.setBackgroundColor(Color.parseColor(DataBean.getRandColor()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.banner_item_rv;
        } else {
            return R.layout.banner_c_item;
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }


    //banner 内部已实现
//    @Override
//    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        Log.e("banner_log", "onViewDetachedFromWindow:" + holder.getAdapterPosition());
//        //定位你的位置
//        if (holder.getAdapterPosition()%2!=0) {
//            if (holder instanceof MyBannerViewHolder) {
//                ((MyBannerViewHolder) holder).banner.stop();
//            }
//        }
//    }
//
//    @Override
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        Log.e("banner_log", "onViewAttachedToWindow:" + holder.getAdapterPosition());
//        if (holder.getAdapterPosition()%2!=0) {
//            if (holder instanceof MyBannerViewHolder) {
//                ((MyBannerViewHolder) holder).banner.start();
//            }
//        }
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    class MyBannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;

        public MyBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}
