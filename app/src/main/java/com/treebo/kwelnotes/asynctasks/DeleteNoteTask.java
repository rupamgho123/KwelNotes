package com.treebo.kwelnotes.asynctasks;

import android.os.AsyncTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class DeleteNoteTask extends AsyncTask<Void,Void,Void>{
  public interface DeleteNoteTaskListener{
    void onDeleteNote();
  }
  long id;
  DeleteNoteTaskListener listener;
  public DeleteNoteTask(long id,DeleteNoteTaskListener listener){
    this.id = id;
    this.listener = listener;
  }

  @Override protected Void doInBackground(Void... params) {
    new NotesDaoImpl().deleteNote(id);
    return null;
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if(listener != null)
      listener.onDeleteNote();
  }
}
