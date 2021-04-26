package com.github.hellforever.skiptest1.arch;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAct<P extends IPresenter, V extends IViewModel<P>> extends AppCompatActivity {
    protected P presenter;
    protected V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        viewModel = initViewModel();
        presenter.bindViewModel(viewModel);
        presenter.init();
        setContentView(viewModel.inflateView(LayoutInflater.from(this), null));
        viewModel.initView();
    }

    protected abstract P initPresenter();

    protected abstract V initViewModel();
}
