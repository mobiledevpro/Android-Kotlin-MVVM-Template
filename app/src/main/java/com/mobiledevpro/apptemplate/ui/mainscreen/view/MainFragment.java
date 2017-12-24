package com.mobiledevpro.apptemplate.ui.mainscreen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cdvdev.commons.fragment.BaseFragment;
import com.mobiledevpro.apptemplate.R;
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IMain;
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.MainPresenter;

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
    protected View populateView(View layoutView, @Nullable Bundle savedInstanceState) {
        return layoutView;
    }

    @Override
    protected void initPresenters() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.bindView(this);
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStartView();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStopView();
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
