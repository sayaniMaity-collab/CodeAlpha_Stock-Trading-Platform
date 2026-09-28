public class Main {
    public static void main(String[] args) {
        // Initialize market
        Market market = new Market();
        market.addStock(new Stock("AAPL", 150.0));
        market.addStock(new Stock("GOOG", 2800.0));
        market.addStock(new Stock("TSLA", 750.0));

        // Display market
        market.displayMarket();

        // Initialize portfolio
        Portfolio portfolio = new Portfolio(5000.0);

        // Load portfolio if file exists
        portfolio.loadFromFile("portfolio.txt");

        // Simulate trading
        portfolio.buyStock(market.getStock("AAPL"), 10);
        portfolio.sellStock(market.getStock("AAPL"), 5);
        portfolio.buyStock(market.getStock("TSLA"), 2);

        // Display portfolio
        portfolio.displayPortfolio();

        // Save portfolio
        portfolio.saveToFile("portfolio.txt");
    }
}

