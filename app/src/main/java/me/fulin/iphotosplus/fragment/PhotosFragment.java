package me.fulin.iphotosplus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;

import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.adapter.PhotosRecyclerViewAdapter;
import me.fulin.iphotosplus.bean.Flickr.FlickrPhotos;
import me.fulin.iphotosplus.net.FlickerFetchr;
import me.fulin.iphotosplus.ui.SpacesItemDecoration;

/**
 * Created by jack on 29/8/15.
 */
public class PhotosFragment extends Fragment {
    private static final String TAG = PhotosFragment.class.getSimpleName();

    private RecyclerView mPhotosRecycleView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private FlickrPhotos mFlickrPhotos;

    private boolean loading = true;
    private int pageIndex = 1;
    private int previousTotal = 0;
    private int visibleThreshold = 5;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        LoadData();
    }

    private void LoadData() {
        FlickerFetchr.fetchItems(pageIndex, new Response.Listener<FlickrPhotos>() {
            @Override
            public void onResponse(FlickrPhotos flickrPhotos) {

                pageIndex++;

                if (null == mFlickrPhotos) {
                    mFlickrPhotos = flickrPhotos;
                    mAdapter = new PhotosRecyclerViewAdapter(getActivity(), mFlickrPhotos);
                    mPhotosRecycleView.setAdapter(mAdapter);
                } else {
                    mFlickrPhotos.getPhotos().getPhoto().addAll(flickrPhotos.getPhotos().getPhoto());
                    mAdapter.notifyItemInserted(mFlickrPhotos.getPhotos().getPhoto().size() - 1);
                    //mAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        mPhotosRecycleView = (RecyclerView) v.findViewById(R.id.fragment_photos_recycler_view);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mPhotosRecycleView.setLayoutManager(mLayoutManager);
//        mPhotosRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mPhotosRecycleView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
//        mPhotosRecycleView.setAdapter(new NormalRecyclerViewAdapter(this));

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mPhotosRecycleView.addItemDecoration(decoration);

        mPhotosRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if (!loading && (visibleItemCount + pastVisiblesItems + visibleThreshold) >= totalItemCount) {
                    loading = true;

                    LoadData();
                }

            }
        });

        return v;
    }

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }
}
