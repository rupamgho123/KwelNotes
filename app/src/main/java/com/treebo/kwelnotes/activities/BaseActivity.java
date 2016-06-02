package com.treebo.kwelnotes.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.treebo.kwelnotes.events.BusProvider;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    BusProvider.getInstance().register(this);
  }

  @Override protected void onDestroy() {
    ButterKnife.unbind(this);
    BusProvider.getInstance().unregister(this);
    super.onDestroy();
  }
}
