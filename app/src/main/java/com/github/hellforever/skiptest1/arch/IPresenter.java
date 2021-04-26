package com.github.hellforever.skiptest1.arch;

public interface IPresenter<V extends IViewModel> {
    void bindViewModel(V viewModel);

    void init();
}
