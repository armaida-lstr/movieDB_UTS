package com.example.moviedb.view.fragments;

import android.app.ApplicationExitInfo;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.adapter.PCompaniesAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Loading;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;

import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private TextView judultampil,genretampil,dateRtampil,avgtampil,populertampil,subtitletampil,tagline;
    private ImageView imageviewtampilback,imageviewdepn, tampilpcmp,imgback;
    private CardView cv_card_nowplaying;
//    RecyclerView rv_pcompanies;
    private ImageButton imageButton_back;
    ActionBar actionBar;

  //  private String movieId="";
   // private MovieViewModel viewmodeltampil;
//    private String Tgenre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private RecyclerView rv_nowplaying, rv_pcompanies;
    private MovieViewModel view_model;
  // private Loading loading = new Loading(MovieDetailsFragment. this);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        judultampil = view.findViewById(R.id.judultampil);
        genretampil = view.findViewById(R.id.genretampil);
        dateRtampil = view.findViewById(R.id.dateRtampil);
        avgtampil = view.findViewById(R.id.avgtampil);
        populertampil = view.findViewById(R.id.populertampil);
        subtitletampil = view.findViewById(R.id.subtitletampil);
        imageviewdepn = view.findViewById(R.id.imageviewdepn);
        imageviewtampilback = view.findViewById(R.id.imageviewtampilback);
        rv_pcompanies = view.findViewById(R.id.rv_pcompanies);
        tampilpcmp = view.findViewById(R.id.tampilpcmp);
        tagline = view.findViewById(R.id.tagline);
        cv_card_nowplaying = view.findViewById(R.id.cv_card_nowplaying);


        String MovieId = getArguments().getString("movieId");

        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getMovieById(MovieId);
        view_model.getResultGetMovieById().observe(getActivity(), showResultMovie);
        return view;
    }
        private Observer<Movies> showResultMovie = new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                String Tjudul = movies.getTitle();
                String Tovw = movies.getOverview();
                String Trelease = movies.getRelease_date();
               String Tpopuler = String.valueOf(movies.getPopularity());
              String tavg = String.valueOf(movies.getVote_average());
                String vcnt = String.valueOf(movies.getVote_count());
                String Tfotodepan = movies.getPoster_path().toString();
                String Tfotoblkng = movies.getBackdrop_path().toString();
                String Ttagline = movies.getTagline();

                LinearLayoutManager pcompany = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
               rv_pcompanies.setLayoutManager(pcompany);
                PCompaniesAdapter adapter = new PCompaniesAdapter(getActivity());
                adapter.setProductionCompaniesList(movies.getProduction_companies());
                rv_pcompanies.setAdapter(adapter);


                String Tgenre="";
                for (int i = 0; i < movies.getGenres().size(); i++) {
                    Tgenre += movies.getGenres().get(i).getName();
                    if (i != movies.getGenres().size() - 1) {
                        Tgenre += ", ";
                    }
                }
                tagline.setText(Ttagline);
                genretampil.setText(Tgenre);
                populertampil.setText(Tpopuler);
                dateRtampil.setText(Trelease);
                subtitletampil.setText(Tovw);
                judultampil.setText(Tjudul);
                avgtampil.setText(tavg +""+ "("+vcnt+")");
                Glide.with( MovieDetailsFragment. this).load(Const.IMAGE_PATH + Tfotodepan).into(imageviewdepn);
            Glide.with(MovieDetailsFragment. this).load(Const.IMAGE_PATH + Tfotoblkng).into(imageviewtampilback);
//

//                cv_card_nowplaying.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        loading.startLoadingDialog();
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                loading.dismiss();
//
//                            }
//                        },3000);
//                    }
//                });


            }
        };
}

//                String Tjudul = getArguments().getString("title");
//                judultampil.setText(Tjudul);
//                String Tovw = getArguments().getString("subjudul");
//                subtitletampil.setText(Tovw);
//                String Trelease = getArguments().getString("release");
//                dateRtampil.setText(Trelease);
//                String Tpopuler = getArguments().getString("populer");
//                populertampil.setText(Tpopuler);
//                String tavg = getArguments().getString("avgt");
//                String vcnt = getArguments().getString("vcount");
////
//                avgtampil.setText(tavg +""+ "("+vcnt+")");
//                String Tfotodepan = getArguments().getString("fotodepan");
//                String Tfotoblkng= getArguments().getString("fotoblkng");
//
//                String movies = getArguments().getParcelable("genre");
//                genretampil.setText(movies);
//
//
//                Glide.with( this).load(Const.IMAGE_PATH + Tfotodepan).into(imageviewdepn);
//                Glide.with( this).load(Const.IMAGE_PATH + Tfotoblkng).into(imageviewtampilback);
//                //  Glide.with( this).load(Const.IMAGE_PATH + Tfotoblkng).into(tampilpcmp);



//
//
//                    textViewgenre.setText(Tgenre);
//                    textViewid.setText(movieId);
//                    textView_tmpl_jdl.setText(Tjudul);
//                    textView_tmpl_subjdl.setText(Tovw);
//                    textView_tmpl_rls.setText(Trelease);
//                    Glide.with(MovieDetailsActivity.this).load(Const.IMAGE_PATH + Tfoto).into(imageViewtampil);
//                };
//
////       };

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager();
//        linearLayoutManager.setOrientation(rv_pcompanies.HORIZONTAL);
//        rv_pcompanies.setLayoutManager(linearLayoutManager);

//                String Tgenre = getArguments().getParcelable()getGenres();
//

////

//    }

//                genretampil.setText(Tgenre);
//        String genre ="";
//
//        for (int i = 0; i < Movies.getGenres().size(); i++){
//            if(i == Movies.getGenres().size() - 1){
//                genre += Movies.getGenres().get(i).getName();
//            }else{
//                genre += Movies.getGenres().get(i).getName()+", ";
//            }
//        }




