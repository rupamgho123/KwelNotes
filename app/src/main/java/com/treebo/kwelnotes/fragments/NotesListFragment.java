package com.treebo.kwelnotes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.treebo.kwelnotes.R;
import com.treebo.kwelnotes.adapters.NotesAdapter;
import com.treebo.kwelnotes.dao.NotesDaoImpl;
import com.treebo.kwelnotes.events.BusProvider;
import com.treebo.kwelnotes.events.FabButtonClickEvent;
import com.treebo.kwelnotes.interfaces.NotesDao;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class NotesListFragment extends BaseFragment {

  public static final String TAG = NotesListFragment.class.getName();
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  NotesDao notesDao;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.notes_list,container,false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initToolbar(toolbar);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(getContext());
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);

    notesDao = new NotesDaoImpl();
    NotesAdapter adapter = new NotesAdapter();
    recyclerView.setAdapter(adapter);

    try {
      adapter.setData(notesDao.getAllNotes());
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }

  @OnClick(R.id.fab) public void onFabClick(View view){
    BusProvider.getInstance().post(new FabButtonClickEvent());
  }
}
