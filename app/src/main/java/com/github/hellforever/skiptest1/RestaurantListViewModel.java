package com.github.hellforever.skiptest1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.hellforever.skiptest1.arch.IViewModel;
import com.github.hellforever.skiptest1.data.Address;
import com.github.hellforever.skiptest1.data.CuisineType;
import com.github.hellforever.skiptest1.data.Deal;
import com.github.hellforever.skiptest1.data.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListViewModel implements IViewModel<RestaurantListPresenter> {

    private RestaurantListPresenter presenter;
    private RestaurantListAct act;
    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;
    private ProgressDialog dialog;

    public RestaurantListViewModel(RestaurantListAct act) {
        this.act = act;
    }

    @Override
    public void bindPresenter(RestaurantListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.skip_restaurant_list_layout, parent);
    }

    @Override
    public void destroy() {
    }


    @Override
    public void initView() {
        recyclerView = act.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(act));
        adapter = new RestaurantListAdapter();
        recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(1, R.id.menu_message_custom_meme, 1, "");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setActionView(R.layout.skip_search_menu_layout);
        SearchView searchView = menuItem.getActionView().findViewById(R.id.menu_message_custom_meme);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    return false;
                } else {
                    InputMethodManager imm = (InputMethodManager) act
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                    presenter.searchRestaurants(query);
                    return true;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void render(List<Restaurant> list) {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
        adapter.setData(list);
    }

    public void showError() {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
        Toast.makeText(act, R.string.error, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        dialog = ProgressDialog.show(act, "Loading", "Wait while loading...");
    }

    class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<Restaurant> list = new ArrayList<>();

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RestaurantViewHolder(act.getLayoutInflater().inflate(R.layout.skip_restaurant_list_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            RestaurantViewHolder viewHolder = (RestaurantViewHolder) holder;
            viewHolder.nameView.setText(list.get(position).getName());
            viewHolder.ratingView.setText(act.getString(R.string.score, list.get(position).getRating().getStarRating()));
            if (list.get(position).getCuisineTypeList() != null) {
                StringBuilder builder = new StringBuilder();
                for (CuisineType type : list.get(position).getCuisineTypeList()) {
                    if (type != null && !TextUtils.isEmpty(type.getName()))
                        builder.append(type.getName()).append(" ");
                }
                viewHolder.cuisineTypeView.setText(builder.toString());
            }
            String imgUrl = list.get(position).getLogoUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                Glide.with(act).load(imgUrl).into(viewHolder.logoView);
            }
            viewHolder.item.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(act);
                View customView = act.getLayoutInflater().inflate(R.layout.skip_restaurant_detail, null);
                builder.setView(customView);
                TextView addressView = customView.findViewById(R.id.address);
                TextView timeView = customView.findViewById(R.id.time);
                TextView dealView = customView.findViewById(R.id.deal);
                if (list.get(position).getAddress() != null) {
                    Address address = list.get(position).getAddress();
                    addressView.setText(act.getString(R.string.address, address.getFistLine(),
                            address.getCity(), address.getPostcode()));
                } else {
                    addressView.setVisibility(View.GONE);
                }
                if (list.get(position).getOpeningTime() != null) {
                    timeView.setText(act.getString(R.string.time, list.get(position).getOpeningTime().getHours() + ""));
                } else {
                    timeView.setVisibility(View.GONE);
                }
                if (list.get(position).getDeals() != null && list.get(position).getDeals().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Deal deal : list.get(position).getDeals()) {
                        if (deal != null && !TextUtils.isEmpty(deal.getDescription())) {
                            stringBuilder.append(deal.getDescription()).append(" ");
                        }
                    }
                    dealView.setText(act.getString(R.string.deal, stringBuilder.toString()));
                } else {
                    dealView.setVisibility(View.GONE);
                }
                builder.show();
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setData(List<Restaurant> list) {
            if (list != null && !list.isEmpty()) {
                this.list = list;
                notifyDataSetChanged();
            }
        }
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView logoView;
        TextView nameView;
        TextView ratingView;
        TextView cuisineTypeView;
        ViewGroup item;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            logoView = itemView.findViewById(R.id.logo);
            nameView = itemView.findViewById(R.id.name);
            ratingView = itemView.findViewById(R.id.score);
            cuisineTypeView = itemView.findViewById(R.id.cuisine_type);
            item = itemView.findViewById(R.id.item);
        }
    }
}
