package com.bw.dliao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.bumptech.glide.Glide;
import com.bw.dliao.R;
import com.bw.dliao.bean.DataBean;
import com.bw.dliao.bean.IndexBean;
import com.bw.dliao.jiekou.OnItemClickListener;
import com.bw.dliao.utils.AMapUtils;
import com.bw.dliao.utils.DeviceUtils;
import com.bw.dliao.utils.DisanceUtils;
import com.bw.dliao.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by muhanxi on 17/7/8.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DataBean> list;
    private Context context;
    private int tag = 1; // 1 先行布局 2 瀑布流
    private int itemWidth ;
    private String s;

    private OnItemClickListener<DataBean> mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<DataBean> listener) {
        this.mOnItemClickListener = listener;
    }


    public IndexAdapter(Context context) {
        this.context = context;
        //当前屏幕 的宽度 除以3
        itemWidth = DeviceUtils.getDisplayInfomation(context).x / 3 ;
    }

    public void setData(IndexBean bean,int page) {
        if (list == null) {
            list = new ArrayList<DataBean>();
        }
        if(page == 1){
            list.clear();
        }
        list.addAll(bean.getData());
        notifyDataSetChanged();
    }

    /**
     * 2 瀑布流
     * 1 线性布局
     *
     * @param type
     */
    public void dataChange(int type) {
        this.tag = type;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.indexfragment_verticaladapter, parent, false);
            final VerticalViewHolder verticalViewHolder = new VerticalViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = verticalViewHolder.getAdapterPosition();
                    final DataBean image = list.get(position);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position, image, v,s);
                    }
                }
            });

            return verticalViewHolder;

        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.indexfragment_pinterest, parent, false);
            final PinterestViewHolder pinterestViewHolder = new PinterestViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = pinterestViewHolder.getAdapterPosition();
                    final DataBean image = list.get(position);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position, image, v,s);
                    }
                }
            });

            return pinterestViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof VerticalViewHolder) {

            //列表的形式展示
            VerticalViewHolder verticalViewHolder = (VerticalViewHolder) holder;

            verticalViewHolder.indexfragmentNickname.setText(list.get(position).getNickname());


            verticalViewHolder.indexfragmentDes.setText(list.get(position).getIntroduce());

            Glide.with(context).load(list.get(position).getImagePath()).error(R.drawable.face_error).into(verticalViewHolder.indexfragmentFace);






           String lat =  PreferencesUtils.getValueByKey(context, AMapUtils.LAT,"");
            String lng = PreferencesUtils.getValueByKey(context,AMapUtils.LNG,"");

            double olat = list.get(position).getLat();
            double olng = list.get(position).getLng() ;


            if(!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng) && olat != 0.0 && olng != 0.0){

                double dlat = Double.valueOf(lat);
                double dlng = Double.valueOf(lng);
                DPoint dPoint = new DPoint(dlat,dlng);
                DPoint oPoint = new DPoint(olat,olng);

                //计算两点之间的距离
               float dis =  CoordinateConverter.calculateLineDistance(dPoint,oPoint);


                s = DisanceUtils.standedDistance(dis);


                verticalViewHolder.indexfragmentAgesex.setText(list.get(position).getAge() + "岁 , " + list.get(position).getGender() + " , " + s);

            } else {

                verticalViewHolder.indexfragmentAgesex.setText(list.get(position).getAge() + "岁 , " + list.get(position).getGender());

            }
        } else {
            PinterestViewHolder staggeredViewHolder = (PinterestViewHolder) holder;

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) staggeredViewHolder.indexfragmentStagger.getLayoutParams() ;

           float scale =  (float) itemWidth / (float) list.get(position).getPicWidth()  ;
            params.width = itemWidth;
            params.height = (int)( (float)scale * (float)list.get(position).getPicHeight()) ;


            staggeredViewHolder.indexfragmentStagger.setLayoutParams(params);


            Glide.with(context).load(list.get(position).getImagePath()).error(R.drawable.face_error).into(staggeredViewHolder.indexfragmentStagger);

        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (tag == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    static class VerticalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.indexfragment_nickname)
        TextView indexfragmentNickname;
        @BindView(R.id.indexfragment_agesex)
        TextView indexfragmentAgesex;
        @BindView(R.id.indexfragment_des)
        TextView indexfragmentDes;
        @BindView(R.id.indexfragment_face)
        CircleImageView indexfragmentFace;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    static class PinterestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.indexfragment_stagger)
        CircleImageView indexfragmentStagger;
        public PinterestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }



}
