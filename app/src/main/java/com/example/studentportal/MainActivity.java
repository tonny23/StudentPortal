package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PortalItemAdapter.ItemClickListener {

    private List<PortalItem> mItems = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerView.Adapter mAdapter;
    private static final int numberOfColumns = 3;

    private static final int RESULT_ADD_URL = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        mItems.add(new PortalItem("VLO", "https://vlo.informatica.hva.nl/"));
        mItems.add(new PortalItem("HVA", "http://www.hva.nl/"));
        mItems.add(new PortalItem("Studiegids", "https://studiegids.hva.nl//"));

        updateUI();
    }

    /**
     * Open the AddWebsiteActivity with a request code
     */
    @OnClick(R.id.fab)
    public void addItem(){
        Intent intent = new Intent(MainActivity.this, AddWebsiteActivity.class);
        startActivityForResult(intent, RESULT_ADD_URL);
    }

    /**
     * Update the data
     */
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalItemAdapter(mItems, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            // Checks if the requestCode is correct
            if (requestCode == RESULT_ADD_URL) {
                // Add the values to the list
                mItems.add(new PortalItem(data.getStringExtra("title"), data.getStringExtra("url")));
                // Refresh the mGridView
                updateUI();
            }
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        PortalItem portalItem = mItems.get(position);

        //open URL in chrome custom tabs
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(portalItem.getUrl()));

    }
}
