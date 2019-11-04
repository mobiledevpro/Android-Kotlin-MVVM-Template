package com.mobiledevpro.apptemplate.ui.mainscreen.view;

import android.os.Bundle;
import android.view.View;

import com.mobiledevpro.apptemplate.R;
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IMain;
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.MainPresenter;
import com.mobiledevpro.commons.fragment.BaseFragment;

import androidx.annotation.Nullable;

/**
 * Main fragment for main activity
 * <p>
 * Created by Dmitriy V. Chernysh on 20.10.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class MainFragment extends BaseFragment implements IMain.View {

    private IMain.Presenter mPresenter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected int getOptionsMenuResId() {
        return R.menu.menu_question_notes_list;
    }

    @Override
    protected View populateView(View layoutView, @Nullable Bundle savedInstanceState) {
        return layoutView;
    }

    @Override
    protected void initPresenters() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.bindView(this);
    }

    @Override
    public void onStop() {
        mPresenter.unbindView();
        super.onStop();
    }

    @Override
    protected int getAppBarTitle() {
        return R.string.app_name_main;
    }

    @Override
    protected int getHomeAsUpIndicatorIcon() {
        return R.drawable.ic_close_24dp;
    }
}
