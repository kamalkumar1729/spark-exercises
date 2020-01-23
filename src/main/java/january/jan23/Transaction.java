package january.jan23;

public class Transaction {
    private String period;
    private String status;
    private String data_value;
    private String kamalName;

    public Transaction() {}

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData_value(String data_value) {
        this.data_value = data_value;
    }

    public String getData_value() {
        return data_value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "period='" + period + '\'' +
                ", status='" + status + '\'' +
                ", data_value=" + data_value +
                ", kamalName='" + kamalName + '\'' +
                '}';
    }
}
