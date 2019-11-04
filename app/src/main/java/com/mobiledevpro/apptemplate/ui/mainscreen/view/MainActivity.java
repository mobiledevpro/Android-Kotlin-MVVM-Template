package com.mobiledevpro.apptemplate.ui.mainscreen.view;

import android.view.View;

import com.mobiledevpro.apptemplate.R;
import com.mobiledevpro.apptemplate.helper.FragmentsHelper;
import com.mobiledevpro.commons.activity.BaseActivity;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void initPresenters() {
        //no need
    }

    @Override
    protected void populateView(View layoutView) {
        //work with view
        FragmentsHelper.showMainFragment(mFragmentManager, R.id.fragment_container);
    }
}
