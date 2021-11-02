package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;

import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewHolderUpComing>{

    private Context context;
    private List<UpComing.Results> UpComingList;

    public List<UpComing.Results> getUpComingList() {
        return UpComingList;
    }

    public void setUpComingList(List<UpComing.Results> upComingList) {
        UpComingList = upComingList;
    }

    public UpComingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolderUpComing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return  new UpComingAdapter.CardViewHolderUpComing(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UpComingAdapter.CardViewHolderUpComing holder, int position) {
        final UpComing.Results results = getUpComingList().get(position);
        holder.title_card.setText(results.getTitle());
        holder.overview_card.setText(results.getOverview());
        holder.release_card.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMAGE_PATH+ results.getPoster_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return getUpComingList().size();
    }

    public class CardViewHolderUpComing extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title_card,overview_card,release_card;
        CardView cv_card_nowplaying;

        public CardViewHolderUpComing(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView);
            title_card = itemView.findViewById(R.id.title_card);
            overview_card = itemView.findViewById(R.id.overview_card);
            release_card = itemView.findViewById(R.id.release_card);
            cv_card_nowplaying = itemView.findViewById(R.id.cv_card_nowplaying);
        }
    }
}
