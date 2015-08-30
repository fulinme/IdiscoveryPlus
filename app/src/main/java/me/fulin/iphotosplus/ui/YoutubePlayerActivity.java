package me.fulin.iphotosplus.ui;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.net.YoutubeConnector;

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView mPlayerView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(me.fulin.iphotosplus.R.layout.activity_youtube_player);

        mPlayerView = (YouTubePlayerView) findViewById(me.fulin.iphotosplus.R.id.player_view);
        try {
            mPlayerView.initialize(YoutubeConnector.KEY, YoutubePlayerActivity.this);
        }
        catch(Exception e)
        {
            Log.d("YoutubePlayerActivity", e.toString());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean restored) {
        if (!restored) {
            player.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }
}