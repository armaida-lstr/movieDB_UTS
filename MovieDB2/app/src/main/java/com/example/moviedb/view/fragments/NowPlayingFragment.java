package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.Loading;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rv_nowplaying;
    private MovieViewModel view_model;
  //  SwipeRefreshLayout srf;
    //  private CardView cv_card_nowplaying;
    //   private Loading loading = new Loading(MovieDetailsFragment. this);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getNowPlaying();
        view_model.getResulNowPlaying().observe(getActivity(), showNowPlaying);

        rv_nowplaying = view.findViewById(R.id.rv_nowplaying);
        //  cv_card_nowplaying = view.findViewById(R.id.cv_card_nowplaying);
//        srf = view.findViewById(R.id.rv_now_playing);
//        srf.setOnRefreshListener(this);
        return view;

    }


    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {


            rv_nowplaying.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_nowplaying.setAdapter(adapter);

//            cv_card_nowplaying.setOnClickListener(new View.OnClickListener() {
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

            ItemClickSupport.addTo(rv_nowplaying).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", "" + nowPlaying.getResults().get(position).getId());

                    final Loading loading = new Loading(NowPlayingFragment.this);
                    loading.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismiss();
                        }
                    }, 1000);
                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_movieDetailsFragment, bundle);

                }
            });


        }


    };
}

//    @Override
//    public void onRefresh() {
//
//    }
//}

//                    bundle.putString("title", "" + nowPlaying.getResults().get(position).getTitle());
//                    bundle.putString("subjudul", "" + nowPlaying.getResults().get(position).getOverview());
//                    bundle.putString("release", "" + nowPlaying.getResults().get(position).getRelease_date());
//                    bundle.putString("populer", "" + nowPlaying.getResults().get(position).getPopularity());
//                    bundle.putString("avgt", "" + nowPlaying.getResults().get(position).getVote_average());
//                    bundle.putString("vcount", "" + nowPlaying.getResults().get(position).getVote_count());
////                  bundle.putString("genre", ""+nowPlaying.getResults().get(position).get());
//                    bundle.putString("fotodepan", "" + nowPlaying.getResults().get(position).getPoster_path());
//                    bundle.putString("fotoblkng", "" + nowPlaying.getResults().get(position).getBackdrop_path());


//

//                        Toast.makeText(getContext(), "Details berhasil dibuka", Toast.LENGTH_SHORT).show();
//

