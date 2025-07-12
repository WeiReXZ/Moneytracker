package wi.exest.moneytracker.ui.main;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.security.AllPermission;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wi.exest.moneytracker.data.local.AppDatabase;
import wi.exest.moneytracker.data.local.TransactionDao;
import wi.exest.moneytracker.data.local.TransactionEntity;
import wi.exest.moneytracker.util.Constants;

public class MainViewModel extends AndroidViewModel {
    private final LiveData<List<TransactionEntity>> allTransactions;
    private final LiveData<Double> balanceLiveData;
    private final TransactionDao transactionDao;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        transactionDao = database.transactionDao();
        allTransactions = transactionDao.getAllTransactions();

        balanceLiveData = Transformations.map(allTransactions, transactions -> {
            double income = 0, expense = 0;
            for (TransactionEntity t : transactions) {
                if (Constants.TYPE_INCOME.equals(t.getType())) {
                    income += t.getAmount();
                } else if (Constants.TYPE_EXPENSE.equals(t.getType())) {
                    expense += t.getAmount();
                }
            }
            return income - expense;
        });
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<Double> getBalanceLiveData() {
        return balanceLiveData;
    }

    public void insertTransaction(TransactionEntity transaction) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> transactionDao.insert(transaction));
    }

    public void deleteTransaction(TransactionEntity transaction) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> transactionDao.delete(transaction));
    }
}