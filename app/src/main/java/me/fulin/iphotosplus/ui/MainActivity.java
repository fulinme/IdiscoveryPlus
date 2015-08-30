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


public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle bundle) {
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.gallary), R.mipmap.ic_star_black_24dp);
        //mHelpLiveo.addSubHeader(getString(R.string.categories));
        mHelpLiveo.add(getString(R.string.youtube), R.mipmap.ic_star_black_24dp);
        //mHelpLiveo.addSeparator(); // Item separator

        with(this).startingPosition(1) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                        //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                        //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                        //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                        //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                        //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                        //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                        //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                        //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                //.footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)

                        //{optional} - Header Customization
                        //.customHeader(mCustomHeader)

                        //{optional} - Footer Customization
                        //.footerNameColor(R.color.nliveo_blue_colorPrimary)
                        //.footerIconColor(R.color.nliveo_blue_colorPrimary)
                        //.footerBackground(R.color.nliveo_white)

                //.setOnClickUser(onClickPhoto)
                //.setOnPrepareOptionsMenu(onPrepare)
                //.setOnClickFooter(onClickFooter)
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
                mFragment = YoutubeFragment.newInstance(0);
            default:
                //mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        //setElevationToolBar(position != 1 ? 15 : 0);

    }
}
