package com.treebo.kwelnotes.interfaces;

import com.treebo.kwelnotes.datamodels.Note;
import java.util.List;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public interface NotesDao {
  boolean createNote(String title,String content);
  Note deleteNote(long id);
  Note getNoteById(long id);
  boolean modifyNote(long id, String title,String content);
  List<Note> getAllNotes();
}
