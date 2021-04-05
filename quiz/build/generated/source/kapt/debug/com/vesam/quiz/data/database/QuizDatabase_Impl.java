package com.vesam.quiz.data.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QuizDatabase_Impl extends QuizDatabase {
  private volatile QuizDAO _quizDAO;

  private volatile DetailsDAO _detailsDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `get_quiz_list_entity` (`created_at` TEXT NOT NULL, `id` INTEGER NOT NULL, `jalali_created_at` TEXT NOT NULL, `pass_condition` INTEGER NOT NULL, `sort` INTEGER NOT NULL, `title` TEXT NOT NULL, `type` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `get_quiz_with_details_entity` (`id` INTEGER NOT NULL, `details` TEXT NOT NULL, `questions` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`id`) REFERENCES `get_quiz_list_entity`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fc2d716e4cd611ffca7609fa3d9a418b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `get_quiz_list_entity`");
        _db.execSQL("DROP TABLE IF EXISTS `get_quiz_with_details_entity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsGetQuizListEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsGetQuizListEntity.put("created_at", new TableInfo.Column("created_at", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("jalali_created_at", new TableInfo.Column("jalali_created_at", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("pass_condition", new TableInfo.Column("pass_condition", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("sort", new TableInfo.Column("sort", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizListEntity.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGetQuizListEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGetQuizListEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGetQuizListEntity = new TableInfo("get_quiz_list_entity", _columnsGetQuizListEntity, _foreignKeysGetQuizListEntity, _indicesGetQuizListEntity);
        final TableInfo _existingGetQuizListEntity = TableInfo.read(_db, "get_quiz_list_entity");
        if (! _infoGetQuizListEntity.equals(_existingGetQuizListEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "get_quiz_list_entity(com.vesam.quiz.data.model.quiz_list.Quiz).\n"
                  + " Expected:\n" + _infoGetQuizListEntity + "\n"
                  + " Found:\n" + _existingGetQuizListEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsGetQuizWithDetailsEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsGetQuizWithDetailsEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizWithDetailsEntity.put("details", new TableInfo.Column("details", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGetQuizWithDetailsEntity.put("questions", new TableInfo.Column("questions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGetQuizWithDetailsEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysGetQuizWithDetailsEntity.add(new TableInfo.ForeignKey("get_quiz_list_entity", "CASCADE", "NO ACTION",Arrays.asList("id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesGetQuizWithDetailsEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGetQuizWithDetailsEntity = new TableInfo("get_quiz_with_details_entity", _columnsGetQuizWithDetailsEntity, _foreignKeysGetQuizWithDetailsEntity, _indicesGetQuizWithDetailsEntity);
        final TableInfo _existingGetQuizWithDetailsEntity = TableInfo.read(_db, "get_quiz_with_details_entity");
        if (! _infoGetQuizWithDetailsEntity.equals(_existingGetQuizWithDetailsEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "get_quiz_with_details_entity(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel).\n"
                  + " Expected:\n" + _infoGetQuizWithDetailsEntity + "\n"
                  + " Found:\n" + _existingGetQuizWithDetailsEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fc2d716e4cd611ffca7609fa3d9a418b", "50d90924218307d2b831aba114a4fd26");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "get_quiz_list_entity","get_quiz_with_details_entity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `get_quiz_list_entity`");
      _db.execSQL("DELETE FROM `get_quiz_with_details_entity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public QuizDAO quizDAO() {
    if (_quizDAO != null) {
      return _quizDAO;
    } else {
      synchronized(this) {
        if(_quizDAO == null) {
          _quizDAO = new QuizDAO_Impl(this);
        }
        return _quizDAO;
      }
    }
  }

  @Override
  public DetailsDAO detailsDAO() {
    if (_detailsDAO != null) {
      return _detailsDAO;
    } else {
      synchronized(this) {
        if(_detailsDAO == null) {
          _detailsDAO = new DetailsDAO_Impl(this);
        }
        return _detailsDAO;
      }
    }
  }
}
