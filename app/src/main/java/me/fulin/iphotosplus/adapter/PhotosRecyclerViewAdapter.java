package me.fulin.iphotosplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.bean.FlickrPhotos;
import me.fulin.iphotosplus.bean.Photo;
import me.fulin.iphotosplus.net.FlickerFetchr;

/**
 * Created by jack on 29/8/15.
 */

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.PhotosViewHolder> {

    private Context mContext;
    private FlickrPhotos mFlickrPhotos;

    public PhotosRecyclerViewAdapter(Context context, FlickrPhotos flickrPhotos) {
        mContext = context;
        mFlickrPhotos = flickrPhotos;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_text_item, parent, false);
                //.inflate(R.layout.item_text, parent, false);

        return new PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        Photo photo = mFlickrPhotos.getPhotos().getPhoto().get(position);
        String imageUrl = FlickerFetchr.IMAGE_URL_FORMAT.replace("{farm-id}", photo.getFarm()+"").
                replace("{server-id}", photo.getServer()).replace("{id}", photo.getId()).replace("{secret}", photo.getSecret());
        Picasso.with(mContext).load(imageUrl).into(holder.mImageView);
        holder.mTextView.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return mFlickrPhotos == null ? 0 : mFlickrPhotos.getPhotos().getPhoto().size();
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        PhotosViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.item_img);
            mTextView = (TextView)view.findViewById(R.id.item_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }
}