package com.treebo.kwelnotes.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.OnClick;
import com.squareup.otto.Subscribe;
import com.treebo.kwelnotes.R;
import com.treebo.kwelnotes.activities.BaseActivity;
import com.treebo.kwelnotes.events.FabButtonClickEvent;
import com.treebo.kwelnotes.events.ListItemClickEvent;
import com.treebo.kwelnotes.fragments.ModifyNoteFragment;
import com.treebo.kwelnotes.fragments.NotesListFragment;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    openNotesListFragment();
  }

  private void openNotesListFragment(){
    getSupportFragmentManager().beginTransaction().
        replace(R.id.base_frame,new NotesListFragment(),NotesListFragment.TAG).
        addToBackStack(NotesListFragment.TAG).commit();
  }

  private void openModifyNoteFragment(long id){
    ModifyNoteFragment fragment = new ModifyNoteFragment();
    Bundle bundle = new Bundle();
    bundle.putLong("id",id);
    fragment.setArguments(bundle);
    getSupportFragmentManager().beginTransaction().
        replace(R.id.base_frame,fragment, ModifyNoteFragment.TAG).
        addToBackStack(NotesListFragment.TAG).commit();
  }

  @Subscribe
  public void onEvent(FabButtonClickEvent event){
    openModifyNoteFragment(0);
  }

  @Subscribe
  public void onEvent(ListItemClickEvent event){
    openModifyNoteFragment(event.getId());
  }

  @Override public void onBackPressed() {
    if(getSupportFragmentManager().getBackStackEntryCount() <= 1)
      finish();
    else
      super.onBackPressed();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
