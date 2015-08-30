package me.fulin.iphotosplus.ui;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import me.fulin.iphotosplus.fragment.FlickrPhotoPageFragment;

/**
 * Created by jack on 30/8/15.
 */
public class PhotoPageActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context context, Uri photoPageUri) {
        Intent i = new Intent(context, PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }


    @Override
    protected Fragment createFragment() {
        return FlickrPhotoPageFragment.newInstance(getIntent().getData());
    }
}
