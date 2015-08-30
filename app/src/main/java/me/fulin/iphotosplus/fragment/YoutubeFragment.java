package me.fulin.iphotosplus.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.fulin.iphotosplus.AppController;
import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.bean.youtube.VideoItem;
import me.fulin.iphotosplus.net.YoutubeConnector;
import me.fulin.iphotosplus.ui.YoutubePlayerActivity;
import me.fulin.iphotosplus.util.Util;

/**
 * Created by jack on 4/8/15.
 */
public class YoutubeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Bind(me.fulin.iphotosplus.R.id.search_input)
    AutoCompleteTextView mSearchInput;
    @Bind(me.fulin.iphotosplus.R.id.videos_found)
    ListView mVideosFound;
    String[] mKeyWords = {"Full Movies", "Funny", "Singapore", "Tedx", "Learn", "English", "Chinese", "Eduction", "CNTV"};
    ArrayAdapter<VideoItem> mAdapter;
    private Handler handler;
    private Thread mThread;
    private int mVisibleThreshold = 2;
    private String mTitle;
    private List<VideoItem> mSearchResults = new ArrayList<VideoItem>();
    private String mNextPageToker = "";
    public YoutubeFragment() {
    }

    public static YoutubeFragment newInstance(int sectionNumber) {
        YoutubeFragment fragment = new YoutubeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private void searchOnYoutube(final String keywords) {

        if (mThread == null || !mThread.isAlive()) {
            mThread = new Thread() {
                public void run() {
                    try {

                        YoutubeConnector yc = new YoutubeConnector(getActivity());
                        yc.setNextPageToken(mNextPageToker);


                        List<VideoItem> newSearchResults = yc.search(keywords);
                        mSearchResults.addAll(newSearchResults);


                        if (mNextPageToker == null || mNextPageToker.length() == 0) {
                            mNextPageToker = yc.getNextPageToken();


                            handler.post(new Runnable() {
                                public void run() {
                                    updateVideosFound();
                                }
                            });
                        } else {
                            mNextPageToker = yc.getNextPageToken();

                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();
        }
    }

    private void updateVideosFound() {
        mAdapter = new ArrayAdapter<VideoItem>(getActivity().getApplicationContext(), me.fulin.iphotosplus.R.layout.video_item, mSearchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(me.fulin.iphotosplus.R.layout.video_item2, parent, false);
                }
                ImageView thumbnail = (ImageView) convertView.findViewById(me.fulin.iphotosplus.R.id.video_thumbnail);
                TextView title = (TextView) convertView.findViewById(R.id.video_title);
                //TextView description = (TextView)convertView.findViewById(com.bestappsclub.demovideo.R.id.video_description);
                try {
                    VideoItem searchResult = mSearchResults.get(position);

                    Picasso.with(getActivity().getApplicationContext()).load(searchResult.getThumbnailURL()).fit().centerCrop().into(thumbnail);
                    title.setText(searchResult.getTitle());
                } catch (Exception e) {

                }
                //description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        addClickListener();
        mVideosFound.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
                    if (totalItemCount == 0) return;
                    //loadNextPage();
                    String tmpKeyword = Util.getTitleBuyPostion(getActivity(), getArguments().getInt(ARG_SECTION_NUMBER));
                    if ((mNextPageToker != null && mNextPageToker.length() != 0) && mSearchInput.getText() != null && mSearchInput.getText().toString().length() != 0) {
                        searchOnYoutube(mSearchInput.getText().toString());
                    } else if (tmpKeyword != "") {
                        searchOnYoutube(tmpKeyword);
                    }

                }
            }
        });
        mVideosFound.setAdapter(mAdapter);

    }

    private void addClickListener() {
        mVideosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent intent = new Intent(AppController.getInstance().getApplicationContext(), YoutubePlayerActivity.class);
                intent.putExtra("VIDEO_ID", mSearchResults.get(pos).getId());

                startActivity(intent);
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_youtube_search, container, false);
        ButterKnife.bind(this, rootView);

        //searchInput.setVisibility(View.GONE);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mKeyWords);
        mSearchInput.setAdapter(adapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    //mThread.stop();
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                }
                return;

            }
        };


        String tmpKeyword = Util.getTitleBuyPostion(getActivity(), getArguments().getInt(ARG_SECTION_NUMBER));
        if (tmpKeyword != "") {
            searchOnYoutube(tmpKeyword);
        }

        mSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() || actionId == EditorInfo.IME_ACTION_DONE) {

                    mSearchResults = new ArrayList<VideoItem>();
                    searchOnYoutube(v.getText().toString());
                    return false;
                }

                return true;
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
