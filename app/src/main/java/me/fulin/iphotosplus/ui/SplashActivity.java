package me.fulin.iphotosplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.util.Util;

public class SplashActivity extends AppCompatActivity {


    ImageView mBackgroundImage;
    TextView mTitleText;
    TextView mVersionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = decorView.getSystemUiVisibility();
        int newUiOptions = uiOptions;
        //隐藏导航栏
        newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(newUiOptions);

        mBackgroundImage = (ImageView) findViewById(R.id.image_background);
        Random random = new Random();
        int num = random.nextInt(5);
        int drawable = R.drawable.splash_bg_1;
        switch (num ){
            case 0:
                break;
            case 1:
                drawable = R.drawable.splash_bg_2;
                break;
            case 2:
                drawable = R.drawable.splash_bg_3;
                break;
            case 3:
                drawable = R.drawable.splash_bg_4;
                break;
            case 4:
                drawable = R.drawable.splash_bg_5;
                break;
        }
        mBackgroundImage.setImageDrawable(getResources().getDrawable(drawable));
        Animation animImage = AnimationUtils.loadAnimation(this, R.anim.image_welcome);
        mBackgroundImage.startAnimation(animImage);
        animImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.no_anim);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mTitleText = (TextView) findViewById(R.id.title_text);
        mVersionText = (TextView) findViewById(R.id.version_text);
        mVersionText.setText("Version："+ Util.getVersion(this));
    }

    @Override
    public void finish() {
        mBackgroundImage.destroyDrawingCache();
        super.finish();
    }
}
