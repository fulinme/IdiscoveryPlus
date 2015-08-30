package me.fulin.iphotosplus.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Random;

/**
 * Created by jack on 4/8/15.
 */
public class Util {

    public static String getTitleBuyPostion(Context context, int position) {

        String mTitle = "";

        switch (position) {
            case 0:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section1);
                break;
            case 1:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section2);
                break;
            case 2:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section3);
                break;

            case 3:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section4);
                break;
            case 4:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section5);
                break;
            case 5:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section6);
                break;
            case 6:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section7);
                break;
            case 7:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section8);
                break;
            case 8:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section9);
                break;
            case 9:
                mTitle = context.getString(me.fulin.iphotosplus.R.string.title_section10);
                break;
        }

        return mTitle;

    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
