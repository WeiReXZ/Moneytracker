package wi.exest.moneytracker.data.local;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __deletionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  public TransactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `transactions` (`id`,`amount`,`category`,`type`,`date`,`comment`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindDouble(2, value.amount);
        if (value.category == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.category);
        }
        if (value.type == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.type);
        }
        if (value.date == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.date);
        }
        if (value.comment == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.comment);
        }
      }
    };
    this.__deletionAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`amount` = ?,`category` = ?,`type` = ?,`date` = ?,`comment` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindDouble(2, value.amount);
        if (value.category == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.category);
        }
        if (value.type == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.type);
        }
        if (value.date == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.date);
        }
        if (value.comment == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.comment);
        }
        stmt.bindLong(7, value.id);
      }
    };
  }

  @Override
  public void insert(final TransactionEntity transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionEntity.insert(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final TransactionEntity transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionEntity.handle(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TransactionEntity transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionEntity.handle(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<TransactionEntity>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<List<TransactionEntity>>() {
      @Override
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TransactionEntity _item;
            _item = new TransactionEntity();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _item.amount = _cursor.getDouble(_cursorIndexOfAmount);
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _item.category = null;
            } else {
              _item.category = _cursor.getString(_cursorIndexOfCategory);
            }
            if (_cursor.isNull(_cursorIndexOfType)) {
              _item.type = null;
            } else {
              _item.type = _cursor.getString(_cursorIndexOfType);
            }
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _item.date = null;
            } else {
              _item.date = _cursor.getString(_cursorIndexOfDate);
            }
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _item.comment = null;
            } else {
              _item.comment = _cursor.getString(_cursorIndexOfComment);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<TransactionEntity> getTransactionById(final int id) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<TransactionEntity>() {
      @Override
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final TransactionEntity _result;
          if(_cursor.moveToFirst()) {
            _result = new TransactionEntity();
            _result.id = _cursor.getInt(_cursorIndexOfId);
            _result.amount = _cursor.getDouble(_cursorIndexOfAmount);
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _result.category = null;
            } else {
              _result.category = _cursor.getString(_cursorIndexOfCategory);
            }
            if (_cursor.isNull(_cursorIndexOfType)) {
              _result.type = null;
            } else {
              _result.type = _cursor.getString(_cursorIndexOfType);
            }
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _result.date = null;
            } else {
              _result.date = _cursor.getString(_cursorIndexOfDate);
            }
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _result.comment = null;
            } else {
              _result.comment = _cursor.getString(_cursorIndexOfComment);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getTransactionCount() {
    final String _sql = "SELECT COUNT(*) FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<String>> getCategoriesByType(final String type) {
    final String _sql = "SELECT DISTINCT category FROM transactions WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactions"}, false, new Callable<List<String>>() {
      @Override
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
