package me.fulin.iphotosplus.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.navigationliveo.NavigationLiveo;
import me.fulin.iphotosplus.R;
import me.fulin.iphotosplus.fragment.PhotosFragment;
import me.fulin.iphotosplus.fragment.YoutubeFragment;
import me.fulin.iphotosplus.util.Util;


public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle bundle) {
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.gallary), R.mipmap.ic_picture);

        mHelpLiveo.addSeparator();

        mHelpLiveo.add(getString(R.string.youtube), R.mipmap.ic_video);

        for (int i=0; i<9; i++)
        {
            mHelpLiveo.add(Util.getTitleBuyPostion(getApplicationContext(), i));
        }

        //mHelpLiveo.addSeparator(); // Item separator

        with(this).startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                .build();

        int position = this.getCurrentPosition();
        //this.setElevationToolBar(position != 2 ? 15 : 0);

    }

    @Override
    public void onItemClick(int position) {

        Fragment mFragment=null;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                mFragment = new PhotosFragment();
                break;
            case 1:
                break;
            default:
                mFragment = YoutubeFragment.newInstance(position-2);
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }


    }
}
