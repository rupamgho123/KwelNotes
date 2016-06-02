package com.treebo.kwelnotes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.treebo.kwelnotes.R;
import com.treebo.kwelnotes.datamodels.Note;
import com.treebo.kwelnotes.events.BusProvider;
import com.treebo.kwelnotes.events.ListItemClickEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rupam.ghosh on 03/06/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> implements
    View.OnClickListener{

  private List<Note> data = new ArrayList<>();

  public void setData(@NonNull List<Note> data){
    this.data = data;
    notifyDataSetChanged();
  }

  @Override public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.
        from(parent.getContext()).
        inflate(R.layout.note_item_layout, parent, false);
    itemView.setOnClickListener(this);
    return new NoteViewHolder(itemView);
  }

  @Override public void onBindViewHolder(NoteViewHolder holder, int position) {
    Note note = data.get(position);
    holder.title.setText(note.title);
    holder.itemView.setTag(note.getId());
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @Override public void onClick(View v) {
    BusProvider.getInstance().post(new ListItemClickEvent(v.getTag()));
  }

  public static class NoteViewHolder extends RecyclerView.ViewHolder {
    protected TextView title;

    public NoteViewHolder(View v) {
      super(v);
      title =  (TextView) v.findViewById(R.id.title_text);
    }
  }
}
