package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;

import java.util.List;

public class PCompaniesAdapter  extends RecyclerView.Adapter<PCompaniesAdapter.CardViewHolderpcompanies>{

    private Context context;
    private List<Movies.ProductionCompanies> productionCompaniesList;

    private List<Movies.ProductionCompanies> getProductionCompaniesList() {
        return productionCompaniesList;
    }

    public void setProductionCompaniesList(List<Movies.ProductionCompanies> productionCompaniesList) {
        this.productionCompaniesList = productionCompaniesList;
    }

    public PCompaniesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolderpcompanies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pcmp, parent, false);
        return  new PCompaniesAdapter.CardViewHolderpcompanies(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PCompaniesAdapter.CardViewHolderpcompanies holder, int position) {
        final Movies.ProductionCompanies pcomp = getProductionCompaniesList().get(position);
        holder.name = pcomp.getName();
        holder.logo_path = pcomp.getLogo_path();
        Glide.with(context)
                .load(Const.IMAGE_PATH+ holder.logo_path)
                .into(holder.tampilpcmp);
        holder.card_tampil_pcmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getProductionCompaniesList().size();
    }

    public class CardViewHolderpcompanies extends RecyclerView.ViewHolder {
        ImageView tampilpcmp;
        String logo_path, name;
CardView card_tampil_pcmp;
        public CardViewHolderpcompanies(@NonNull View itemView) {
            super(itemView);
            tampilpcmp = itemView.findViewById(R.id.tampilpcmp);
            card_tampil_pcmp = itemView.findViewById(R.id.card_tampil_pcmp);
        }
    }
}
