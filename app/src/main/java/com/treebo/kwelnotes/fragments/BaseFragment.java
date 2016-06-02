package com.treebo.kwelnotes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.treebo.kwelnotes.activities.BaseActivity;
import com.treebo.kwelnotes.events.BusProvider;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public abstract class BaseFragment extends Fragment {
  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    BusProvider.getInstance().register(this);
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    BusProvider.getInstance().unregister(this);
    super.onDestroyView();
  }

  protected void initToolbar(Toolbar toolbar){
    BaseActivity activity = (BaseActivity)getActivity();
    activity.setSupportActionBar(toolbar);
    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  protected void showSnackbar(String content){
    Snackbar.make(getView(),content,Snackbar.LENGTH_SHORT).show();
  }

  protected void showToast(String content){
    Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
  }
}
