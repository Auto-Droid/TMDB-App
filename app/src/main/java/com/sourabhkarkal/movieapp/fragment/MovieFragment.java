package com.sourabhkarkal.movieapp.fragment;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabhkarkal.movieapp.ApplicationController;
import com.sourabhkarkal.movieapp.R;
import com.sourabhkarkal.movieapp.listener.OnLoadMoreListener;
import com.sourabhkarkal.movieapp.modal.MoviePageDTO;
import com.sourabhkarkal.movieapp.modal.MoviesDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMoviesDTO;
import com.sourabhkarkal.movieapp.service.DataRequest;
import com.sourabhkarkal.movieapp.service.DataResponse;
import com.sourabhkarkal.movieapp.service.RestTemplateExecutor;
import com.sourabhkarkal.movieapp.utils.Constants;
import com.sourabhkarkal.movieapp.utils.HttpOperation;
import com.sourabhkarkal.movieapp.utils.ManagePreferences;
import com.sourabhkarkal.movieapp.utils.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sourabhkarkal on 24/02/17.
 *
 * This class shows the list of movies form {@link #apiCall(int)} pass 1 for intial page
 * to automatically handle pagination call {@link #callMovieApi()} method ,Page No is stored in Preference
 * with the help of class {@link ManagePreferences} with Constant {@link ManagePreferences#PAGE_NO}
 * <p>
 * For Sorting Data according to release date ref {@link #loadDataFromDB()} where data is fetched according to
 * release date stored in realmDB
 * <p>
 * {@link MoviesAdapter#setOnLoadMoreListener} is added to show infinite loading progress bar
 * {@link #swipeRefreshLayout} is used for showing pull to refresh where always page 1 is passed to get the latest data
 *
 *
 * Realm DB table {@link RMoviesDTO}
 *
 */

public class MovieFragment extends BaseFragment {

    SwipeRefreshLayout swipeRefreshLayout;
    private boolean loading;
    ArrayList<MoviesDTO> moviesDTOs;
    final String TAG = getClass().getName();
    private RecyclerView rvMovieList;
    private MoviesAdapter moviesAdapter;
    private final int MOVIE_TASK_ID = 100;
    int PAGE_NO = 1;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_main, container, false);
        moviesDTOs = new ArrayList<>();

        rvMovieList = (RecyclerView) rootView.findViewById(R.id.rvMovieList);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        PAGE_NO = ManagePreferences.getIntData(getActivity(), ManagePreferences.PAGE_NO); //setting stored pageNo

        rvMovieList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieList.setItemAnimator(new DefaultItemAnimator());
        moviesAdapter = new MoviesAdapter();
        rvMovieList.setAdapter(moviesAdapter);

        loadDataFromDB(); // Fetch data from DB

        if (moviesDTOs.isEmpty())
            callMovieApi();

        moviesAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (moviesDTOs != null) {
                            moviesDTOs.add(null);
                            moviesAdapter.notifyDataSetChanged();
                            callMovieApi();
                        }
                    }
                }, 500);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Utils.isNetworkAvailable()) {
                    apiCall(1); // Passing the first page to get the latest data
                } else {
                    Utils.showSnackBar(rootView, getString(R.string.no_internet_available), Utils.MessageType.ERROR);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        return rootView;
    }

    private void callMovieApi() {
        if (Utils.isNetworkAvailable()) {
            apiCall(PAGE_NO);
            PAGE_NO ++;
        } else {
            Utils.showSnackBar(rootView, getString(R.string.no_internet_available), Utils.MessageType.ERROR);
            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
            if(loading)
                if (moviesDTOs != null && moviesDTOs.size() > 0) {
                    if (moviesDTOs.get(moviesDTOs.size() - 1) == null) {
                        moviesDTOs.remove(moviesDTOs.size() - 1);
                        moviesAdapter.notifyItemRemoved(moviesDTOs.size());
                        moviesAdapter.setLoaded();
                    }
                }
        }
    }

    private void apiCall(int pageNo) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        DataRequest dataRequest = new DataRequest(MOVIE_TASK_ID, Constants.MAIN_URL + Constants.LIST_URL + "api_key=" + Constants.API_KEY + "&primary_release_date.lte=" + formattedDate + "&sort_by=release_date.desc&page=" + PAGE_NO, getActivity(), MovieFragment.this, HttpOperation.GET, null);
        new RestTemplateExecutor().callServerApi(dataRequest);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(moviesDTOs!=null)
            moviesDTOs = null;
    }

    //region ************* fetch data from realm *************
    private void loadDataFromDB() {
        Realm realm = Realm.getInstance(ApplicationController.getInstance().getRealmConfig());
        RealmResults<RMoviesDTO> rMoviesDTOs = realm.where(RMoviesDTO.class).findAllSorted("release_date", Sort.DESCENDING);
        for (RMoviesDTO rMoviesDTO : rMoviesDTOs) {
            moviesDTOs.add(new MoviesDTO(rMoviesDTO));
        }
        moviesAdapter.notifyDataSetChanged();
        realm.close();
    }
    //endregion


    //region ************* OnTaskComplete Method *************
    @Override
    public void onTaskComplete(int taskId, Object object, boolean isError) {
        super.onTaskComplete(taskId, object, isError);

        if (MOVIE_TASK_ID == taskId) {

            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);

            //   remove progress item
            if (loading)
                if (moviesDTOs != null && moviesDTOs.size() > 0) {
                    if (moviesDTOs.get(moviesDTOs.size() - 1) == null) {
                        moviesDTOs.remove(moviesDTOs.size() - 1);
                        moviesAdapter.notifyItemRemoved(moviesDTOs.size());
                        moviesAdapter.setLoaded();
                    }
                }

            if (isError)
                Log.e(TAG, object.toString());
            else {
                DataResponse dataResponse = (DataResponse) object;
                MoviePageDTO moviePageDTO = null;
                try {
                    moviePageDTO = new ObjectMapper().readValue(dataResponse.getObject().toString(), MoviePageDTO.class);

                    ManagePreferences.setIntData(getActivity(), ManagePreferences.PAGE_NO, moviePageDTO.getPage());// Store PageNo

                    Realm realm = Realm.getInstance(ApplicationController.getInstance().getRealmConfig());
                    for (MoviesDTO moviesDTO : moviePageDTO.getResults()) {
                        RMoviesDTO rMoviesDTO = new RMoviesDTO(moviesDTO);
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(rMoviesDTO);
                        realm.commitTransaction();
                    }
                    realm.close();
                    moviesDTOs.clear();
                    loadDataFromDB();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //endregion

    //region ************* Movie Adapter CODE *************
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MoviesDTO> moviesList;
        private OnLoadMoreListener onLoadMoreListener;
        private int totalItemCount, visibleItemCount, displayedPosition;

        public class MovieViewHolder extends RecyclerView.ViewHolder {

            TextView tvLanguage, tvMovieTitle, tvMovieReleaseDate, tvVotesPercent, tvVotesCount;
            ImageView imgBanner;
            Button btnBook;
            CardView cardLayout;

            public MovieViewHolder(View view) {
                super(view);
                tvLanguage = (TextView) view.findViewById(R.id.tvLanguage);
                tvMovieTitle = (TextView) view.findViewById(R.id.tvMovieTitle);
                tvVotesCount = (TextView) view.findViewById(R.id.tvVotesCount);
                tvVotesPercent = (TextView) view.findViewById(R.id.tvVotesPercent);
                tvMovieTitle = (TextView) view.findViewById(R.id.tvMovieTitle);
                tvMovieReleaseDate = (TextView) view.findViewById(R.id.tvMovieReleaseDate);
                imgBanner = (ImageView) view.findViewById(R.id.imgBanner);
                btnBook = (Button) view.findViewById(R.id.btnBook);
                cardLayout = (CardView) view.findViewById(R.id.cardView);
            }
        }


        public MoviesAdapter() {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rvMovieList.getLayoutManager();

            rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView,
                                       int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    displayedPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    if (dy > 0) //check for scroll down
                    {
                        if (!loading)
                            if ((visibleItemCount + displayedPosition) >= totalItemCount) {
                                // End has been reached
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                    loading = true;
                                }
                            }
                    }
                }
            });

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MovieViewHolder) {
                final MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
                final MoviesDTO movie = moviesDTOs.get(position);
                movieViewHolder.tvMovieTitle.setText(movie.getTitle()); // title
                movieViewHolder.tvLanguage.setText(movie.getOriginal_language().toUpperCase()); // Language
                movieViewHolder.tvMovieReleaseDate.setText(movie.getRelease_date()); // Release Date
                movieViewHolder.tvVotesPercent.setText("❤ " + ((int) (movie.getPopularity())) + "");
                movieViewHolder.tvVotesCount.setText(movie.getVote_count() + " votes");

                // Banner Image
                Glide.with(MovieFragment.this)
                        .load(Constants.IMAGE_BASE_PATH + (movie.getBackdrop_path() == null ? movie.getPoster_path() : movie.getBackdrop_path()))
                        .animate(android.R.anim.fade_in)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.movie_placeholder)
                        .centerCrop()
                        .into(movieViewHolder.imgBanner);

                // Click action on Book
                movieViewHolder.btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.replaceFragment(getActivity(), new WebViewFragment(), true);
                    }
                });

                // Click action for detail screen
                movieViewHolder.cardLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", movie.getId());
                        movieDetailFragment.setArguments(bundle);
                        Utils.replaceFragment(getActivity(), movieDetailFragment, true);
                    }
                });
            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder vh;
            if (viewType == VIEW_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.movie_item, parent, false);

                vh = new MovieViewHolder(v);
            } else {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.progressbar_item, parent, false);

                vh = new ProgressViewHolder(v);
            }
            return vh;
        }

        public class ProgressViewHolder extends RecyclerView.ViewHolder {
            public ProgressBar progressBar;

            public ProgressViewHolder(View v) {
                super(v);
                progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
            }
        }

        // Return the size of your itemsData (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return moviesDTOs != null ? moviesDTOs.size() : 0;
        }

        @Override
        public int getItemViewType(int position) {
            return moviesDTOs.get(position) != null ? VIEW_ITEM : VIEW_PROG;
        }

        public void setLoaded() {
            loading = false;
        }

        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
        }
    }
    //endregion
}
