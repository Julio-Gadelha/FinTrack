package dio.fintrack.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private String description;
    private BigDecimal value;
    private Type type;
    private LocalDate date;

    public Transaction() {

    }

    public Transaction(Integer id, String description, BigDecimal value, Type type, LocalDate date) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.type = type;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}