package com.sourabhkarkal.movieapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabhkarkal.movieapp.ApplicationController;
import com.sourabhkarkal.movieapp.R;
import com.sourabhkarkal.movieapp.modal.MovieDetailDTO;
import com.sourabhkarkal.movieapp.modal.MoviesDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMovieDetailDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMoviesDTO;
import com.sourabhkarkal.movieapp.service.DataRequest;
import com.sourabhkarkal.movieapp.service.DataResponse;
import com.sourabhkarkal.movieapp.service.RestTemplateExecutor;
import com.sourabhkarkal.movieapp.utils.Constants;
import com.sourabhkarkal.movieapp.utils.HttpOperation;
import com.sourabhkarkal.movieapp.utils.Utils;
import com.sourabhkarkal.movieapp.widget.AutoScrollViewPager;
import com.sourabhkarkal.movieapp.widget.ImageViewPager;
import java.io.IOException;
import java.util.ArrayList;


import io.realm.Realm;

/**
 * Created by sourabhkarkal on 25/02/17.
 *
 * This fragment shows the detail page for the Movie
 * {@link #viewPager} viewPager is added to show multiple images and auto scrolling is done using {@link AutoScrollViewPager}
 * Title is set on {@link #mToolbar} which is an object CollapsingToolbarLayout
 *
 * Data is fetched according to {@link #movieId} which is passed by {@link MovieFragment} as an argument
 *
 *
 * Realm DB table {@link RMovieDetailDTO}
 */

public class MovieDetailFragment extends BaseFragment {

    private View rootView;
    private final int MOVIE_DETAIL_TASK_ID = 101;
    final String TAG = getClass().getName();
    CollapsingToolbarLayout mToolbar;
    private TextView tvDetailTagline, tvDetailWebsite, tvDetailDescription, tvDetailDuration;
    private TextView tvDetailBudget, tvDetailRevenue, tvDetailCountry, tvDetailPopularity;
    private TextView tvDetailLanguage, tvDetailGerene, tvDetailVotesPercent, tvDetailVotesCount;
    String movieId;
    AutoScrollViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_detail_layout, container, false);
        init(rootView);

        movieId = getArguments().getString("id");

        Realm realm = Realm.getInstance(ApplicationController.getInstance().getRealmConfig());
        RMovieDetailDTO rMovieDetailDTO = realm.where(RMovieDetailDTO.class).equalTo("id", Integer.parseInt(movieId)).findFirst();
        if (rMovieDetailDTO != null)
            setData(new MovieDetailDTO(rMovieDetailDTO));
        realm.close();

        if (Utils.isNetworkAvailable()) {
            DataRequest dataRequest = new DataRequest(MOVIE_DETAIL_TASK_ID, Constants.MAIN_URL + Constants.DETAIL_URL + movieId + "?api_key=" + Constants.API_KEY, getActivity(), MovieDetailFragment.this, HttpOperation.GET, null);
            new RestTemplateExecutor().callServerApi(dataRequest);
        } else {
            Utils.showSnackBar(rootView, getString(R.string.no_internet_available), Utils.MessageType.ERROR);
        }


        return rootView;
    }

    //region ************* intialize view *************
    private void init(View rootView) {
        mToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar);
        tvDetailTagline = (TextView) rootView.findViewById(R.id.tvDetailTagline);
        tvDetailWebsite = (TextView) rootView.findViewById(R.id.tvDetailWebsite);
        tvDetailBudget = (TextView) rootView.findViewById(R.id.tvDetailBudget);
        tvDetailRevenue = (TextView) rootView.findViewById(R.id.tvDetailRevenue);
        tvDetailCountry = (TextView) rootView.findViewById(R.id.tvDetailCountry);
        tvDetailLanguage = (TextView) rootView.findViewById(R.id.tvDetailLanguage);
        tvDetailGerene = (TextView) rootView.findViewById(R.id.tvDetailGerene);
        tvDetailVotesPercent = (TextView) rootView.findViewById(R.id.tvDetailVotesPercent);
        tvDetailVotesCount = (TextView) rootView.findViewById(R.id.tvDetailVotesCount);
        tvDetailDescription = (TextView) rootView.findViewById(R.id.tvDetailDescription);
        tvDetailPopularity = (TextView) rootView.findViewById(R.id.tvDetailPopularity);
        tvDetailDuration = (TextView) rootView.findViewById(R.id.tvDetailDuration);
        viewPager = (AutoScrollViewPager) rootView.findViewById(R.id.autoScrollViewPager);

        makeClickable(rootView, R.id.btnDetailBook);
    }
    //endregion


    //region ************* OnTaskComplete Method *************
    @Override
    public void onTaskComplete(int taskId, Object object, boolean isError) {
        super.onTaskComplete(taskId, object, isError);
        if (MOVIE_DETAIL_TASK_ID == taskId) {

            if (isError)
                Log.e(TAG, object.toString());
            else {
                DataResponse dataResponse = (DataResponse) object;
                MovieDetailDTO movieDetailDTO;
                try {
                    movieDetailDTO = new ObjectMapper().readValue(dataResponse.getObject().toString(), MovieDetailDTO.class);
                    Realm realm = Realm.getInstance(ApplicationController.getInstance().getRealmConfig());
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(new RMovieDetailDTO(movieDetailDTO));
                    realm.commitTransaction();
                    RMoviesDTO rMoviesDTO = realm.where(RMoviesDTO.class).equalTo("id", movieId).findFirst();
                    setData(movieDetailDTO);
                    realm.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //endregion

    //region ************* Set Detail Screen Data *************
    private void setData(MovieDetailDTO movieDetailDTO) {

        mToolbar.setTitle(movieDetailDTO.getTitle());
        mToolbar.setExpandedTitleColor(Utils.getColor(getActivity(), R.color.white));
        mToolbar.setCollapsedTitleTextColor(Utils.getColor(getActivity(), R.color.white));

        mToolbar.setBackgroundColor(Utils.getColor(getActivity(), R.color.semi_transparent));
        mToolbar.setStatusBarScrimColor(Utils.getColor(getActivity(), R.color.semi_transparent));
        mToolbar.setContentScrimColor(Utils.getColor(getActivity(), R.color.semi_transparent));

        tvDetailTagline.setText(movieDetailDTO.getTagline() == null || movieDetailDTO.getTagline().isEmpty() ? "N/A" : movieDetailDTO.getTagline());
        tvDetailWebsite.setText(movieDetailDTO.getHomepage() == null || movieDetailDTO.getHomepage().isEmpty() ? "N/A" : movieDetailDTO.getHomepage());
        tvDetailBudget.setText("" + movieDetailDTO.getBudget());
        tvDetailRevenue.setText("" + movieDetailDTO.getRevenue());
        tvDetailCountry.setText(movieDetailDTO.getProduction_countries().isEmpty() ? "N/A" : TextUtils.join(",", movieDetailDTO.getProduction_countries()));
        tvDetailGerene.setText(movieDetailDTO.getGenres().isEmpty() ? "N/A" : TextUtils.join(",", movieDetailDTO.getGenres()));
        tvDetailVotesPercent.setText("" + movieDetailDTO.getVote_average() + "/10");
        tvDetailVotesCount.setText(movieDetailDTO.getVote_count() + " votes");
        tvDetailDescription.setText(movieDetailDTO.getOverview() == null || movieDetailDTO.getOverview().isEmpty() ? "N/A" : movieDetailDTO.getOverview());
        tvDetailLanguage.setText(movieDetailDTO.getOriginal_language().toUpperCase());
        tvDetailPopularity.setText("❤ " + ((int) (movieDetailDTO.getPopularity())));

        int time = movieDetailDTO.getRuntime();
        tvDetailDuration.setText((time / 60) + " hrs " + (time % 60) + " min");


        // Adding all available images to list
        ArrayList<String> imagePathList = new ArrayList<>();
        if (movieDetailDTO.getBelongs_to_collection() != null) {
            if (movieDetailDTO.getBelongs_to_collection().getBackdrop_path() != null)
                imagePathList.add(movieDetailDTO.getBelongs_to_collection().getBackdrop_path());
            if (movieDetailDTO.getBelongs_to_collection().getPoster_path() != null)
                imagePathList.add(movieDetailDTO.getBelongs_to_collection().getPoster_path());
        }
        if (movieDetailDTO.getBackdrop_path() != null)
            imagePathList.add(movieDetailDTO.getBackdrop_path());
        if (movieDetailDTO.getPoster_path() != null)
            imagePathList.add(movieDetailDTO.getPoster_path());

        ImageViewPager adapter = new ImageViewPager(getActivity(), imagePathList);
        viewPager.setAdapter(adapter);
        viewPager.startAutoScroll();
        viewPager.setInterval(3000);
        viewPager.setCycle(true);
        viewPager.setStopScrollWhenTouch(true);

    }
    //endregion

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btnDetailBook) {
            Utils.replaceFragment(getActivity(), new WebViewFragment(), true);
        }
    }
}
