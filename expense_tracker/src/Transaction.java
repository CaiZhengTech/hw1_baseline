import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

  private final double amount;
  private final String category;
  private final String timestamp;

  public Transaction(double amount, String category) {
    // Optional: basic validation to keep the object always-valid
    if (category == null || category.trim().isEmpty()) {
      throw new IllegalArgumentException("category cannot be empty");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("amount cannot be negative");
    }

    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  public double getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }
  
  public String getTimestamp() {
    return timestamp;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    return sdf.format(new Date());
  }
}