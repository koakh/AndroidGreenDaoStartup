package com.koakh.greendaostartup.model.repositories;

import android.content.Context;

import com.koakh.greendaostartup.app.Singleton;
import com.koakh.greendaostartup.model.Note;
import com.koakh.greendaostartup.model.NoteDao;

import java.util.List;

/**
 * Created by mario on 31/01/2015.
 */
public class NoteRepository {

  private static NoteDao getNoteDao(Context context) {
    return ((Singleton) context.getApplicationContext()).getDaoSession().getNoteDao();
  }

  public static Note get(Context context, long id) {
    return getNoteDao(context).load(id);
  }

  public static List<Note> getAll(Context context) {
    return getNoteDao(context).loadAll();
  }

  public static void insertOrUpdate(Context context, Note note) {
    getNoteDao(context).insertOrReplace(note);
  }

  public static void delete(Context context, long id) {
    getNoteDao(context).delete(get(context, id));
  }

  public static void clear(Context context) {
    getNoteDao(context).deleteAll();
  }

}
