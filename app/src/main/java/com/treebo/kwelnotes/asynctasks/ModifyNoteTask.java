package com.treebo.kwelnotes.asynctasks;

import android.os.AsyncTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class ModifyNoteTask extends AsyncTask<Void,Void,Void> {
  public interface ModifyNoteTaskListener{
    void onNoteModified();
  }
  ModifyNoteTaskListener listener;
  long id;
  String title;
  String content;
  public ModifyNoteTask(ModifyNoteTaskListener listener,long id,String title,String content){
    this.listener = listener;
    this.id = id;
    this.title =title;
    this.content = content;
  }

  @Override protected Void doInBackground(Void... params) {
    new NotesDaoImpl().modifyNote(id,title,content);
    return null;
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if(listener != null)
      listener.onNoteModified();
  }
}
