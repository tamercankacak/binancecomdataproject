import com.google.gson.Gson;
import java.util.TimerTask;

public class SchedulerLayer extends TimerTask {

    @Override
    public void run() {
        try {
            BaseAccesLayer baseAccesLayer = new BaseAccesLayer();
            DatabaseHelper _dbHelper = new DatabaseHelper();
            Gson gson = new Gson();




            String[] cryptoList = {"BTCBUSD", "BNBBTC", "ETHBTC", "XRPBTC", "BCHBTC", "LTCBTC"};

            for (String symbol : cryptoList) {
                String json = baseAccesLayer.HTTP_GET(symbol);
                if (!json.isEmpty())
                    _dbHelper.setData(gson.fromJson(json, CryptoMoneyModel.class));

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
