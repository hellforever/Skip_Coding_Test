package com.github.hellforever.skiptest1;

import com.github.hellforever.skiptest1.arch.IPresenter;

public class RestaurantListPresenter implements IPresenter<RestaurantListViewModel> {

    protected RestaurantListViewModel viewModel;

    public RestaurantListPresenter() {
    }

    @Override
    public void bindViewModel(RestaurantListViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.bindPresenter(this);
    }

    @Override
    public void init() {
        viewModel.showProgressDialog();
        RestaurantModel.getInstance().getListRestaurant(" ec4m").subscribe(list -> {
            viewModel.render(list);
        }, t -> {
            viewModel.showError();
        });
    }

    public void searchRestaurants(String postcode) {
        RestaurantModel.getInstance().getListRestaurant(postcode).subscribe(list -> {
            viewModel.render(list);
        }, t -> {
            viewModel.showError();
        });
    }
}
