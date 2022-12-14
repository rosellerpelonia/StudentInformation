package com.example.crud;

import android.view.View;

/**
 * Created by bryanvillanueva on 1/29/2018
 */

public interface RecyclerItemClickListener {
    void onItemClick(View v, int position);
    void onLongItemClick(View v, int position);
}
