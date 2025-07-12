package wi.exest.moneytracker.ui.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

import wi.exest.moneytracker.R;
import wi.exest.moneytracker.data.local.TransactionEntity;
import wi.exest.moneytracker.ui.main.MainViewModel;

public class AddTransactionFragment extends Fragment {
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        EditText amountInput = view.findViewById(R.id.amountInput);
        EditText categoryInput = view.findViewById(R.id.categoryInput);
        RadioGroup typeGroup = view.findViewById(R.id.typeGroup);

        view.findViewById(R.id.saveButton).setOnClickListener(v -> {
            double amount = Double.parseDouble(amountInput.getText().toString());
            String category = categoryInput.getText().toString();
            String type = typeGroup.getCheckedRadioButtonId() == R.id.incomeRadio ? "income" : "expense";

            TransactionEntity transaction = new TransactionEntity();
            transaction.amount = amount;
            transaction.category = category;
            transaction.type = type;
            transaction.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            viewModel.insertTransaction(transaction);
            getParentFragmentManager().popBackStack();
        });

        return view;
    }
}