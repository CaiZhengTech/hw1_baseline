

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private List<Transaction> transactions = new ArrayList<>();

  

  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }

  public ExpenseTrackerView(DefaultTableModel model) {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger
    this.model = model;

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    amountField = new JTextField(10);
    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  // Return an immutable view (immutability on getter)
  public List<Transaction> getTransactions() {
    // defensive copy + unmodifiable
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

  private void refreshTable() {
    model.setRowCount(0);
    int rowNum = 0;
    double totalCost = 0;

    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }

    for (Transaction t : transactions) {
      model.addRow(new Object[]{++rowNum, t.getAmount(), t.getCategory(), t.getTimestamp()});
    }

    model.addRow(new Object[]{"Total", null, null, totalCost});

    // Prefer revalidate/repaint to updateUI()
    transactionsTable.revalidate();
    transactionsTable.repaint();
  }  

  public void refresh() {
    refreshTable();
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
    // remove direct model mutation to avoid double-adding / inconsistency
    // getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
  }


  // Other view methods
}
