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
import com.treebo.kwelnotes.asynctasks.GetNotesTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;
import com.treebo.kwelnotes.datamodels.Note;
import com.treebo.kwelnotes.events.BusProvider;
import com.treebo.kwelnotes.events.FabButtonClickEvent;
import com.treebo.kwelnotes.interfaces.NotesDao;
import java.util.List;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class NotesListFragment extends BaseFragment implements GetNotesTask.GetNotesTaskListener{

  public static final String TAG = NotesListFragment.class.getName();
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  NotesAdapter adapter;

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
    adapter = new NotesAdapter();
    recyclerView.setAdapter(adapter);

    new GetNotesTask(this).execute();
  }

  @OnClick(R.id.fab) public void onFabClick(View view){
    BusProvider.getInstance().post(new FabButtonClickEvent());
  }

  @Override public void onFinish(List<Note> notes) {
    try {
      adapter.setData(notes);
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }
}
