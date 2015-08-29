package me.fulin.iphotosplus.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by jack on 29/8/15.
 */
public class VolleyUtil {

        private static RequestQueue mRequestQueue;

        public static RequestQueue getRequestQueue(Context context)
        {
            if (null == mRequestQueue)
            {
                mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
            return mRequestQueue;
        }

        public static void addRequest(Request<?> request, Object tag) {
            if (tag != null) {
                request.setTag(tag);
            }
            mRequestQueue.add(request);

        }

        public static <T> void addToRequestQueue(Request<T> req) {
            mRequestQueue.add(req);
        }

        public static void cancelPendingRequests(Object tag) {
            mRequestQueue.cancelAll(tag);
        }


        private static ImageLoader mImageLoader;

        public static ImageLoader getImageLoader() {

            /*if (null == mImageLoader)
            {
                mImageLoader = new ImageLoader(mRequestQueue,
                        new ImageLoader.ImageCache() {
                            private final LruCache<String, Bitmap>
                                    cache = new LruCache<String, Bitmap>(20);

                            @Override
                            public Bitmap getBitmap(String url) {
                                return cache.get(url);
                            }

                            @Override
                            public void putBitmap(String url, Bitmap bitmap) {
                                cache.put(url, bitmap);
                            }
                        });
            }*/

            return mImageLoader;
        }
}
