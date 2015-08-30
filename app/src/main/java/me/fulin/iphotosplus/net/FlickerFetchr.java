package me.fulin.iphotosplus.net;

import android.net.Uri;

import com.android.volley.Response;
import com.android.volley.request.GsonRequest;

import me.fulin.iphotosplus.bean.Flickr.FlickrPhotos;
import me.fulin.iphotosplus.util.VolleyHelper;

/**
 * Created by jack on 29/8/15.
 */
public class FlickerFetchr {

    public static String IMAGE_URL_FORMAT = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_m.jpg";

    private static final String TAG = FlickerFetchr.class.getSimpleName();

    private static final String API_KEY = "";

    private static String PageIndex="1";

    private static String url;

    public static void fetchItems(int pageIndex ,Response.Listener<FlickrPhotos> listener, Response.ErrorListener errorListener)
    {
        PageIndex = Integer.toString(pageIndex);

        url = Uri.parse("https://api.flickr.com/services/rest/").buildUpon()
                .appendQueryParameter("method", "flickr.photos.getRecent")
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("per_page","10")
                .appendQueryParameter("extras", "url_s")
                .appendQueryParameter("page", PageIndex)
                .build().toString();

        GsonRequest<FlickrPhotos> myReq = new GsonRequest<FlickrPhotos>(
                    url,
                    FlickrPhotos.class,
                    null,
                    listener,
                    errorListener);

        VolleyHelper.getInstance().getRequestQueue().add(myReq);
    }
}
