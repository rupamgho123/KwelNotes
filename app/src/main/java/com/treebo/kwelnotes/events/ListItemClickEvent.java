package com.treebo.kwelnotes.events;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class ListItemClickEvent {

  private Object id;

  public Long getId() {
    return (Long)id;
  }

  public ListItemClickEvent(Object id){
    this.id = id;
  }
}
