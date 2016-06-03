package com.treebo.kwelnotes.asynctasks;

import android.os.AsyncTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class CreateNoteTask extends AsyncTask<Void,Void,Void> {
  public interface CreateNoteTaskListener{
    void onNoteCreated();
  }
  CreateNoteTaskListener listener;
  String title;
  String content;
  public CreateNoteTask(CreateNoteTaskListener listener,String title,String content){
    this.listener = listener;
    this.title = title;
    this.content = content;
  }
  @Override protected Void doInBackground(Void... params) {
    new NotesDaoImpl().createNote(title,content);
    return null;
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if(listener != null)
      listener.onNoteCreated();
  }
}
