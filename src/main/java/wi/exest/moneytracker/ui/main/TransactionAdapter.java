package wi.exest.moneytracker.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import wi.exest.moneytracker.R;
import wi.exest.moneytracker.data.local.TransactionEntity;
import wi.exest.moneytracker.util.Constants;

public class TransactionAdapter extends ListAdapter<TransactionEntity, TransactionAdapter.TransactionViewHolder> {

    // Интерфейс для обработки кликов
    public interface OnItemClickListener {
        void onItemClick(TransactionEntity transaction);
        void onItemLongClick(TransactionEntity transaction);
    }

    private final OnItemClickListener listener;

    // Конструктор
    public TransactionAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    // DiffUtil для оптимизации обновлений
    public static final DiffUtil.ItemCallback<TransactionEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TransactionEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TransactionEntity oldItem, @NonNull TransactionEntity newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TransactionEntity oldItem, @NonNull TransactionEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    // ViewHolder
    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView amountText;
        TextView categoryText;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            amountText = itemView.findViewById(R.id.amountText);
            categoryText = itemView.findViewById(R.id.categoryText);
        }

        // Метод для привязки данных и обработчиков событий
        public void bind(TransactionEntity transaction, OnItemClickListener listener) {
            String sign = transaction.getType().equals(Constants.TYPE_INCOME) ? "+" : "-";
            amountText.setText(sign + transaction.getAmount() + " ₽");
            categoryText.setText(transaction.getCategory());

            // Обработка клика
            itemView.setOnClickListener(v -> listener.onItemClick(transaction));

            // Обработка долгого клика
            itemView.setOnLongClickListener(v -> {
                listener.onItemLongClick(transaction);
                return true; // true — событие обработано
            });
        }
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionEntity transaction = getItem(position);
        holder.bind(transaction, listener);
    }
}