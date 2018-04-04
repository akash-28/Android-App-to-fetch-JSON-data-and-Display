package com.recyclerview;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AKASH on 9/5/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ListItemModel> listItems ;
    private Context context;

    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener Listener){
        mListener = Listener;
    }


    public Adapter(List<ListItemModel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemModel listitem = listItems.get(position);

        holder.textViewHead.setText(listitem.getHead());
        holder.textViewDesc.setText(listitem.getDesc());

        Picasso.with(context).load(listitem.getImageUrl()).placeholder(R.drawable.placeholder_fooditemlist).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHeading);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewBody);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener!=null){

                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){

                            mListener.onItemClick(position);


                        }
                    }

                }
            });

        }
    }

}
