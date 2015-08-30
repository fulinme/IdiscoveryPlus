package me.fulin.iphotosplus.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.fulin.iphotosplus.R;

public class FlickrPhotoPageFragment extends Fragment {

    private static final String ARG_URI = "photo_page_url";
    private Uri mUri;
    private WebView mWebView;

    public static FlickrPhotoPageFragment newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        FlickrPhotoPageFragment fragment = new FlickrPhotoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUri = getArguments().getParcelable(ARG_URI);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_page, container, false);
        mWebView = (WebView) v.findViewById(R.id.fragment_photo_page_webView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        Log.d("**url**", mUri.toString());
        mWebView.loadUrl(mUri.toString());


        return v; }

}
