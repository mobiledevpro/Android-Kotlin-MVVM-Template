package com.mobiledevpro.apptemplate.ui.mainscreen.presenter;

/**
 * Presenter for main screen
 * <p>
 * Created by Dmitriy V. Chernysh on 20.10.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class MainPresenter implements IMain.Presenter {

    private IMain.View mView;

    @Override
    public void bindView(IMain.View view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }
}
