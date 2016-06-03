package com.treebo.kwelnotes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.treebo.kwelnotes.R;
import com.treebo.kwelnotes.Utils;
import com.treebo.kwelnotes.asynctasks.CreateNoteTask;
import com.treebo.kwelnotes.asynctasks.DeleteNoteTask;
import com.treebo.kwelnotes.asynctasks.ModifyNoteTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;
import com.treebo.kwelnotes.datamodels.Note;
import com.treebo.kwelnotes.interfaces.NotesDao;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class ModifyNoteFragment extends BaseFragment implements
    DeleteNoteTask.DeleteNoteTaskListener, CreateNoteTask.CreateNoteTaskListener,
    ModifyNoteTask.ModifyNoteTaskListener{
  public static final String TAG = ModifyNoteFragment.class.getName();
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.content) EditText content;
  @Bind(R.id.title) EditText title;
  @Bind(R.id.delete_note_button) Button deleteNoteButton;
  @Bind(R.id.edit_note_button) Button editNoteButton;
  @Bind(R.id.save_note_button) Button saveNoteButton;
  long id;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.create_note, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initToolbar(toolbar);
    Bundle arguments = getArguments();
    if (arguments != null) {
      id = arguments.getLong("id");
      if (id != 0) {
        enableEditTexts(false);
        Note note = new NotesDaoImpl().getNoteById(id);
        content.setText(note.content);
        title.setText(note.title);
        toolbar.setTitle("Modify Note");
        editNoteButton.setVisibility(View.VISIBLE);
        return;
      }
    }
    toolbar.setTitle("Create a note");
    saveNoteButton.setVisibility(View.VISIBLE);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    outState.putLong("id",id);
    super.onSaveInstanceState(outState);
  }

  @OnClick(R.id.save_note_button) public void onSaveClicked(View v) {
    if (Utils.isEmpty(title.getText().toString())) {
      showSnackbar("Title cannot be empty");
    } else if (Utils.isEmpty(content.getText().toString())) {
      showSnackbar("Content cannot be empty");
    } else {
      if (id != 0) {
        new ModifyNoteTask(this,id,title.getText().toString(), content.getText().toString()).execute();
      }else {
        new CreateNoteTask(this,title.getText().toString(), content.getText().toString()).execute();
      }
    }
  }

  @OnClick(R.id.delete_note_button) public void onDeleteClicked(View v){
    new DeleteNoteTask(id,this).execute();
  }

  @OnClick(R.id.edit_note_button) public void onEditClicked(View v){
    enableEditTexts(true);
    editNoteButton.setVisibility(View.GONE);
    saveNoteButton.setVisibility(View.VISIBLE);
    deleteNoteButton.setVisibility(View.VISIBLE);
  }

  private void enableEditTexts(boolean enable){
    content.setFocusable(enable);
    content.setFocusableInTouchMode(enable);
    content.setClickable(enable);

    title.setFocusable(enable);
    title.setFocusableInTouchMode(enable);
    title.setClickable(enable);
  }

  @Override public void onDeleteNote() {
    showToast("Note deleted successfully");
    getFragmentManager().popBackStackImmediate();
  }

  @Override public void onNoteCreated() {
    showToast("Note created successfully");
    getFragmentManager().popBackStackImmediate();
  }

  @Override public void onNoteModified() {
    showToast("Note modified successfully");
    getFragmentManager().popBackStackImmediate();
  }
}
