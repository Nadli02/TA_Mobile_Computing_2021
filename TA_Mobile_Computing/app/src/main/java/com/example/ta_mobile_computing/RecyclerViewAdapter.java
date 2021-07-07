package com.example.ta_mobile_computing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final ArrayList<ItemModel> dataItem;
    private Context mContext;

    public class  ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTittle;
        public TextView tvSubtitle;
        public ImageView image;
        public CardView cardViewt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.icon) ;
            tvTittle = (TextView) itemView.findViewById(R.id.tv_sub);
            tvSubtitle = (TextView) itemView.findViewById(R.id.tv_tit);
            cardViewt = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    RecyclerViewAdapter(ArrayList<ItemModel> data, Context mContext) {

        this.dataItem = data;
        this.mContext = mContext;
    }

    @NonNull     @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_recycler_view_adapter, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvTittle.setText(dataItem.get(position).getName());
        viewHolder.tvSubtitle.setText(dataItem.get(position).getType());
        viewHolder.image.setImageResource(dataItem.get(position).getImage());

        viewHolder.cardViewt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = dataItem.get(position).getName();
                if (name == "Solo Leveling") {
                    String url = "https://mangafunny.com/manga/solo-leveling-manhwa-3/";
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(openWebsite);
                }
                else if (name == "The Beginning After The End"){
                    String url = "https://mangaowls.com/single/48021/the-beginning-after-the-end";
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(openWebsite);
                }
                else if (name == "Unordinary"){
                    String url = "https://www.webtoons.com/en/super-hero/unordinary/list?title_no=679&page=1";
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(openWebsite);
                }
                else if (name == "Omniscient Reader") {
                    String url = "https://mangaclash.com/manga/omniscient-readers-viewpoint/";
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(openWebsite);
                }
                else if (name == "The Gamer") {
                    String url = "https://www.webtoons.com/en/action/the-gamer/list?title_no=88&page=1";
                    Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(openWebsite);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataItem.size();
    }

}