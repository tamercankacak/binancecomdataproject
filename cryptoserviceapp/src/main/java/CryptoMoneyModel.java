public class CryptoMoneyModel {
    String symbol;
    String price;

    public CryptoMoneyModel() {

    }
    public CryptoMoneyModel(String symbol, String price) {
        symbol = this.symbol;
        price = this.price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
