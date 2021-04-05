package com.vesam.quiz.data.database;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vesam.quiz.data.model.quiz_list.Quiz;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QuizDAO_Impl implements QuizDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Quiz> __insertionAdapterOfQuiz;

  private final EntityDeletionOrUpdateAdapter<Quiz> __updateAdapterOfQuiz;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public QuizDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuiz = new EntityInsertionAdapter<Quiz>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `get_quiz_list_entity` (`created_at`,`id`,`jalali_created_at`,`pass_condition`,`sort`,`title`,`type`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Quiz value) {
        if (value.getCreatedAt() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCreatedAt());
        }
        stmt.bindLong(2, value.getId());
        if (value.getJalaliCreatedAt() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJalaliCreatedAt());
        }
        stmt.bindLong(4, value.getPassCondition());
        stmt.bindLong(5, value.getSort());
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
      }
    };
    this.__updateAdapterOfQuiz = new EntityDeletionOrUpdateAdapter<Quiz>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `get_quiz_list_entity` SET `created_at` = ?,`id` = ?,`jalali_created_at` = ?,`pass_condition` = ?,`sort` = ?,`title` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Quiz value) {
        if (value.getCreatedAt() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCreatedAt());
        }
        stmt.bindLong(2, value.getId());
        if (value.getJalaliCreatedAt() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJalaliCreatedAt());
        }
        stmt.bindLong(4, value.getPassCondition());
        stmt.bindLong(5, value.getSort());
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        stmt.bindLong(8, value.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM GET_QUIZ_LIST_ENTITY";
        return _query;
      }
    };
  }

  @Override
  public Object insertQuiz(final Quiz quiz, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuiz.insert(quiz);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object insertListQuiz(final List<Quiz> listQuiz, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuiz.insert(listQuiz);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object updateQuiz(final Quiz quiz, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfQuiz.handle(quiz);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object delete(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getQuiz(final Continuation<? super Quiz> p0) {
    final String _sql = "select * from GET_QUIZ_LIST_ENTITY";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<Quiz>() {
      @Override
      public Quiz call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfJalaliCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "jalali_created_at");
          final int _cursorIndexOfPassCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_condition");
          final int _cursorIndexOfSort = CursorUtil.getColumnIndexOrThrow(_cursor, "sort");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final Quiz _result;
          if(_cursor.moveToFirst()) {
            final String _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpJalaliCreatedAt;
            _tmpJalaliCreatedAt = _cursor.getString(_cursorIndexOfJalaliCreatedAt);
            final int _tmpPassCondition;
            _tmpPassCondition = _cursor.getInt(_cursorIndexOfPassCondition);
            final int _tmpSort;
            _tmpSort = _cursor.getInt(_cursorIndexOfSort);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result = new Quiz(_tmpCreatedAt,_tmpId,_tmpJalaliCreatedAt,_tmpPassCondition,_tmpSort,_tmpTitle,_tmpType);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }

  @Override
  public Object getListQuiz(final Continuation<? super List<Quiz>> p0) {
    final String _sql = "select * from GET_QUIZ_LIST_ENTITY";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<List<Quiz>>() {
      @Override
      public List<Quiz> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfJalaliCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "jalali_created_at");
          final int _cursorIndexOfPassCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_condition");
          final int _cursorIndexOfSort = CursorUtil.getColumnIndexOrThrow(_cursor, "sort");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<Quiz> _result = new ArrayList<Quiz>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Quiz _item;
            final String _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpJalaliCreatedAt;
            _tmpJalaliCreatedAt = _cursor.getString(_cursorIndexOfJalaliCreatedAt);
            final int _tmpPassCondition;
            _tmpPassCondition = _cursor.getInt(_cursorIndexOfPassCondition);
            final int _tmpSort;
            _tmpSort = _cursor.getInt(_cursorIndexOfSort);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new Quiz(_tmpCreatedAt,_tmpId,_tmpJalaliCreatedAt,_tmpPassCondition,_tmpSort,_tmpTitle,_tmpType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }
}
