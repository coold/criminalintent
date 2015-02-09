package ru.coold.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by rz on 09.02.2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
