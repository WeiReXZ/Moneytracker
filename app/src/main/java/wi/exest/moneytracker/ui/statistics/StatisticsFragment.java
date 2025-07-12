package wi.exest.moneytracker.ui.statistics;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import wi.exest.moneytracker.R;
import wi.exest.moneytracker.data.local.TransactionEntity;
import wi.exest.moneytracker.ui.main.MainViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StatisticsFragment extends Fragment {

    private TextView incomeText, expenseText, balanceText;
    private View incomeBar, expenseBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        incomeText = view.findViewById(R.id.incomeText);
        expenseText = view.findViewById(R.id.expenseText);
        balanceText = view.findViewById(R.id.balanceText);
        incomeBar = view.findViewById(R.id.incomeBar);
        expenseBar = view.findViewById(R.id.expenseBar);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getAllTransactions().observe(getViewLifecycleOwner(), transactions -> {
            updateStatistics(transactions);
        });

        return view;
    }

    private void updateStatistics(List<TransactionEntity> transactions) {
        double income = 0;
        double expense = 0;

        for (TransactionEntity t : transactions) {
            if (t.getType().equals("income")) {
                income += t.getAmount();
            } else {
                expense += t.getAmount();
            }
        }

        double balance = income - expense;

        // Обновляем текст
        incomeText.setText("Доходы: " + String.format("%.2f ₽", income));
        expenseText.setText("Расходы: " + String.format("%.2f ₽", expense));
        balanceText.setText("Баланс: " + String.format("%.2f ₽", balance));

        // Обновляем полосы
        int maxBarWidth = getResources().getDisplayMetrics().widthPixels - 100; // Максимальная ширина

        float incomeBarWidth = (float) (maxBarWidth * (income / (income + expense + 0.1)));
        float expenseBarWidth = (float) (maxBarWidth * (expense / (income + expense + 0.1)));

        incomeBar.getLayoutParams().width = (int) incomeBarWidth;
        expenseBar.getLayoutParams().width = (int) expenseBarWidth;

        incomeBar.requestLayout();
        expenseBar.requestLayout();
    }
}