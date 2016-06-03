package com.treebo.kwelnotes.asynctasks;

import android.os.AsyncTask;
import com.treebo.kwelnotes.dao.NotesDaoImpl;
import com.treebo.kwelnotes.datamodels.Note;
import com.treebo.kwelnotes.interfaces.NotesDao;
import java.util.List;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class GetNotesTask extends AsyncTask<Void,Void,Void>{

  List<Note> allNotes;

  @Override protected Void doInBackground(Void... params) {
    NotesDao notesDao = new NotesDaoImpl();
    allNotes = notesDao.getAllNotes();
    return null;
  }
  GetNotesTaskListener listener;
  public GetNotesTask(GetNotesTaskListener listener){
    this.listener =listener;
  }
  public interface GetNotesTaskListener{
     void onFinish(List<Note> notes);
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if(listener != null)
      listener.onFinish(allNotes);
  }
}
