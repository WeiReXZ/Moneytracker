package wi.exest.moneytracker.data.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {TransactionEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "money_tracker.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    // Вспомогательный класс для работы с базой в фоне
    public static class InsertTransactionAsyncTask extends AsyncTask<TransactionEntity, Void, Void> {
        private final TransactionDao transactionDao;

        private InsertTransactionAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactions) {
            transactionDao.insert(transactions[0]);
            return null;
        }
    }

    public static class DeleteTransactionAsyncTask extends AsyncTask<TransactionEntity, Void, Void> {
        private final TransactionDao transactionDao;

        private DeleteTransactionAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactions) {
            transactionDao.delete(transactions[0]);
            return null;
        }
    }
}