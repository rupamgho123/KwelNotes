package com.treebo.kwelnotes;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.treebo.kwelnotes.datamodels.Note;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class KwelNotesApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    try {
      Configuration.Builder configurationBuilder = new Configuration.Builder(this);
      configurationBuilder.addModelClass(Note.class);
      ActiveAndroid.initialize(configurationBuilder.create(),true);
      createTableNotes();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createTableNotes(){
    SQLiteDatabase database = ActiveAndroid.getDatabase();
    database.beginTransaction();
    database.execSQL("CREATE TABLE IF NOT EXISTS NOTES( _ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT, LAST_MODIFIED TEXT)");
    database.setTransactionSuccessful();
    database.endTransaction();
    database.close();
  }
}
