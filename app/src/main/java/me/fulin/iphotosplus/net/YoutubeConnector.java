package me.fulin.iphotosplus.net;

import android.content.Context;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.bean.youtube.VideoItem;

public class YoutubeConnector {
    // Your developer key goes here
    public static final String KEY = "";
    private static final long MAXRESULT = 30;
    private YouTube mYoutube;
    private YouTube.Search.List mQuery;
    private String mNextPageToken = "";
    private String mPrePageToken = "";


    public YoutubeConnector(Context context) {
        mYoutube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {
            }
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try {
            mQuery = mYoutube.search().list("id,snippet");
            mQuery.setKey(KEY);
            mQuery.setType("video");
            //mQuery.setPageToken()
            mQuery.setMaxResults(MAXRESULT);

            mQuery.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/medium/url),nextPageToken");
        } catch (IOException e) {
        }
    }


    public String getNextPageToken() {
        return mNextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        mNextPageToken = nextPageToken;
    }

    public List<VideoItem> search(String keywords) {
        mQuery.setQ(keywords);
        if (null != mNextPageToken) {
            mQuery.setPageToken(mNextPageToken);
        }

        try {
            SearchListResponse response = mQuery.execute();

            List<SearchResult> results = response.getItems();
            mNextPageToken = response.getNextPageToken();
            mPrePageToken = response.getPrevPageToken();

            List<VideoItem> items = new ArrayList<VideoItem>();
            for (SearchResult result : results) {
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                //item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getMedium().getUrl());
                //item.setThumbnailURL(result.getSnippet().getThumbnails().getHigh().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        } catch (IOException e) {
            return null;
        }
    }
}