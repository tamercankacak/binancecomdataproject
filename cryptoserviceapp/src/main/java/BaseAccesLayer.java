import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseAccesLayer {
    public String HTTP_GET(String symbol){
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + symbol);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            return result.toString();
        }catch (Exception exception){
            exception.printStackTrace();
            return "";
        }
    }
}
