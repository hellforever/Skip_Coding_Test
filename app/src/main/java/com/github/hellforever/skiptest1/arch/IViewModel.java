package com.github.hellforever.skiptest1.arch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public interface IViewModel<P extends IPresenter> {

    void bindPresenter(P presenter);

    View inflateView(LayoutInflater inflater, ViewGroup parent);

    /**
     * release resource
     */
    void destroy();


    void initView();
}
