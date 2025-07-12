package wi.exest.moneytracker.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(TransactionEntity transaction);


    @Update
    void update(TransactionEntity transaction);


    @Delete
    void delete(TransactionEntity transaction);

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<TransactionEntity>> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE id = :id")
    LiveData<TransactionEntity> getTransactionById(int id);


    @Query("SELECT COUNT(*) FROM transactions")
    LiveData<Integer> getTransactionCount();

    @Query("SELECT DISTINCT category FROM transactions WHERE type = :type")
    LiveData<List<String>> getCategoriesByType(String type);
}