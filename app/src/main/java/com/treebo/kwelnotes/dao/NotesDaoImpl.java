package com.treebo.kwelnotes.dao;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.treebo.kwelnotes.datamodels.Note;
import com.treebo.kwelnotes.interfaces.NotesDao;
import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class NotesDaoImpl implements NotesDao{
  @Override public boolean createNote(@NonNull String title, @NonNull String content) {
    Note note = new Note();
    note.title = title;
    note.content = content;
    note.lastModified = Long.toString(System.currentTimeMillis());
    note.save();
    return true;
  }

  @Override public Note deleteNote(long id) {
    Note note = getNoteById(id);
    note.delete();
    return note;
  }

  @Override public Note getNoteById(long id) {
    Note note =  Model.load(Note.class,id);
    if(note == null)
      throw new IllegalStateException("note not found with id "+id);
    return note;
  }

  @Override public boolean modifyNote(long id, @NonNull String title, @NonNull String content) {
    Note note = getNoteById(id);
    note.title = title;
    note.content = content;
    note.lastModified = Long.toString(System.currentTimeMillis());
    note.save();
    return true;
  }

  @Override public List<Note> getAllNotes() {
    return new Select()
        .from(Note.class)
        .orderBy("last_modified DESC")
        .execute();
  }
}
