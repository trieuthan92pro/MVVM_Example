package com.example.musicapp_mvvm.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.musicapp_mvvm.R;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        setupViewFragment();
    }

    public static DiscoverViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory
                .getInstance(activity.getApplication());
        DiscoverViewModel viewModel =
                ViewModelProviders.of(activity).get(DiscoverViewModel.class);
        return viewModel;
    }

    private void setupViewFragment() {
        DiscoverFragment discoverFragment =
                (DiscoverFragment) getSupportFragmentManager().findFragmentById(R.id.frame_discover);
        if (discoverFragment == null) {
            discoverFragment = DiscoverFragment.getInstance();
            replaceFragmentInActivity(
                    getSupportFragmentManager(), discoverFragment, R.id.frame_discover);
        }
    }

    private void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                           @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
}
