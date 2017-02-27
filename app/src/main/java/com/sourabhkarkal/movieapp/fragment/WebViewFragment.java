package com.sourabhkarkal.movieapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sourabhkarkal.movieapp.R;

/**
 * Created by sourabhkarkal on 25/02/17.
 *
 * This class is called when user taps on book button used in {@link MovieFragment} {@link MovieDetailFragment}
 *
 */

public class WebViewFragment extends BaseFragment {

    private WebView web;
    private ProgressBar progressBar;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        web = (WebView) rootView.findViewById(R.id.webview);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);

        web.setWebViewClient(new MoiveWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.cathaycineplexes.com.sg");
        return rootView;
    }

    //region ************* MoiveWebClient Code *************
    public class MoiveWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }
    //endregion
}
