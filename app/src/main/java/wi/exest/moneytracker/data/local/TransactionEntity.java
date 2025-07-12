package wi.exest.moneytracker.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "transactions")
public class TransactionEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public double amount;
    public String category;
    public String type;
    public String date;
    public String comment;

    public TransactionEntity(int id, double amount, String category, String type, String date, String comment) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
        this.comment = comment;
    }

    public TransactionEntity() {}

    // Геттеры
    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    // equals(), hashCode(), toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                Objects.equals(category, that.category) &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, category, type, date, comment);
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}