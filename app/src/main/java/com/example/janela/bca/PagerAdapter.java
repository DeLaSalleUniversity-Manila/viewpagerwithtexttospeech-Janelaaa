package com.example.janela.bca;

/**
 * Created by Janela on 10/26/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Janela on 10/19/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter{


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    FragmentOne q = new FragmentOne();

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0){
            case 0:
                return new FragmentOne();


            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
