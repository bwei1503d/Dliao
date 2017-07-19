package com.bw.dliao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.dliao.R;
import com.bw.dliao.bean.DataBean1;

import java.util.List;

public class Fragment_second_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    List<DataBean1> list;
    Context context;
    public Fragment_second_Adapter(List<DataBean1> list, Context context) {
        if (list == null) {
            return;
        }
        this.list = list;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragmentsecond_itme, null);
        RecyclerView.ViewHolder  viewHolder = new Fristhader(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Fristhader vholder = (Fristhader) holder;
 /*       RoundingParams params = RoundingParams.asCircle();
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy draweeHierarchy = builder.setFadeDuration(300)
                .setFailureImage(R.mipmap.ic_launcher)
                .setPlaceholderImage(R.mipmap.ic_launcher)
                .setRoundingParams(params)
                .build();

        Uri uri = Uri.parse(list.get(position).getImagePath());
        vholder.iv.getHierarchy();
        vholder.iv.setHierarchy(draweeHierarchy);
        vholder.iv.setImageURI(uri);*/

        Glide.with(context).load(list.get(position).getImagePath()).error(R.drawable.face_error).into(vholder.iv);

        vholder.tilte.setText( list.get(position).getNickname() + " " +  list.get(position).getPhone());
        vholder.infor.setText("签名：" + list.get(position).getIntroduce());
        vholder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClickListener(position, v,list.get(position).getUserId());
            }
        });
        //系统的点击事件
        vholder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击时：调用接口的方法
                listener.onItemClickListener(position, v,list.get(position).getUserId());
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class Fristhader extends RecyclerView.ViewHolder {

        private final TextView infor;
        private final TextView tilte;
        private final ImageView iv;
        private final LinearLayout lin;

        public Fristhader(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.fragment_frist_title_ivs);
            tilte = (TextView) itemView.findViewById(R.id.fragment_frist_title_named);
            infor = (TextView) itemView.findViewById(R.id.fragment_frist_title_sex_inford);
            lin = (LinearLayout) itemView.findViewById(R.id.frist_adapter_lined);
        }


    }


    //自建里点击事件
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view,int userid);

        void onItemLongClickListener(int position, View view);
    }


}
