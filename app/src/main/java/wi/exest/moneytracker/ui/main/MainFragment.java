package wi.exest.moneytracker.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import wi.exest.moneytracker.R;
import wi.exest.moneytracker.data.local.TransactionEntity;
import wi.exest.moneytracker.ui.add.AddTransactionFragment;
import wi.exest.moneytracker.ui.statistics.StatisticsFragment;
import wi.exest.moneytracker.util.Constants;

public class MainFragment extends Fragment {

    private MainViewModel viewModel;
    private TransactionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new TransactionAdapter(new TransactionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TransactionEntity transaction) {
                // Редактирование транзакции
                Bundle args = new Bundle();
                args.putInt("transaction_id", transaction.getId());
                AddTransactionFragment editFragment = new AddTransactionFragment();
                editFragment.setArguments(args);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, editFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onItemLongClick(TransactionEntity transaction) {
                // Удаление транзакции
                viewModel.deleteTransaction(transaction);
            }
        });
        recyclerView.setAdapter(adapter);

        // ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Подписка на баланс
        viewModel.getBalanceLiveData().observe(getViewLifecycleOwner(), balance -> {
            TextView balanceText = view.findViewById(R.id.balanceText);
            balanceText.setText("Баланс: " + String.format("%.2f ₽", balance));
        });

        // Подписка на список транзакций
        viewModel.getAllTransactions().observe(getViewLifecycleOwner(), transactions -> {
            adapter.submitList(transactions);
        });

        // Кнопка добавления
        MaterialButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, new AddTransactionFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // Кнопка "Подробнее" (переход к статистике)
        view.findViewById(R.id.statsButton).setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, new StatisticsFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}