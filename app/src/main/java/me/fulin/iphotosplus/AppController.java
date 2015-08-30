package me.fulin.iphotosplus;

import android.app.Application;

import me.fulin.iphotosplus.util.VolleyHelper;

/**
 * Created by jack on 25/8/15.
 */
public class AppController extends Application{

    public static final String TAG = AppController.class.getSimpleName();


    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}