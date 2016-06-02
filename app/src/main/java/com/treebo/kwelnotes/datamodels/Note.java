package com.treebo.kwelnotes.datamodels;

import android.provider.BaseColumns;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rupam.ghosh on 03/06/16.
 */

@Table(name = "notes",id = BaseColumns._ID)
public class Note extends Model {

  @Column(name = "title")
  public String title;

  @Column(name = "content")
  public String content;

  @Column(name = "last_modified")
  public String lastModified;
}
