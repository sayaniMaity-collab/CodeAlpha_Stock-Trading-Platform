import java.util.HashMap;
import java.util.Map;

public class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void displayMarket() {
        System.out.println("Market Data:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
}

