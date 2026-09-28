import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double cashBalance;

    public Portfolio(double initialBalance) {
        this.cashBalance = initialBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cashBalance >= cost) {
            cashBalance -= cost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Not enough balance to buy!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (owned >= quantity) {
            holdings.put(stock.getSymbol(), owned - quantity);
            cashBalance += stock.getPrice() * quantity;
            System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    public void displayPortfolio() {
        System.out.println("Cash Balance: $" + cashBalance);
        System.out.println("Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " shares");
        }
    }

    // Save portfolio to file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(cashBalance + "\n");
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            System.out.println("Portfolio saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving portfolio: " + e.getMessage());
        }
    }

    // Load portfolio from file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            holdings.clear();
            cashBalance = Double.parseDouble(reader.readLine());
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                holdings.put(parts[0], Integer.parseInt(parts[1]));
            }
            System.out.println("Portfolio loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading portfolio: " + e.getMessage());
        }
    }
}
