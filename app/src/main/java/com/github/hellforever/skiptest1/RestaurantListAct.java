package com.github.hellforever.skiptest1;

import android.view.Menu;

import com.github.hellforever.skiptest1.arch.BaseAct;

public class RestaurantListAct extends BaseAct<RestaurantListPresenter, RestaurantListViewModel> {
    @Override
    protected RestaurantListPresenter initPresenter() {
        return new RestaurantListPresenter();
    }

    @Override
    protected RestaurantListViewModel initViewModel() {
        return new RestaurantListViewModel(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return viewModel.onCreateOptionsMenu(menu);
    }
}
