import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private Connection connection;

    public DatabaseHelper() {
        try {
            Gson gson = new Gson();
            JsonReader jsonFile = new JsonReader(new FileReader("Settings.json"));
            SettingsModel settings = gson.fromJson(jsonFile, SettingsModel.class);
            connection = DriverManager.getConnection(settings.ConnectionUrl, settings.UserName, settings.Password);

            System.out.println("Connection OK !");
            createTables();
        } catch (SQLException exception) {
            System.out.println("Error Connect Database ! Please Check Database Connect");
            exception.printStackTrace();
            System.exit(1);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public void createTables() {
        try {
            String _sqlText = "CREATE TABLE IF NOT EXISTS public.cryptomoneys\n" +
                    "(\n" +
                    "    \"integer\" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    symbol character varying(30) COLLATE pg_catalog.\"default\",\n" +
                    "    insertdate timestamp(6) without time zone,\n" +
                    "    price double precision,\n" +
                    "    CONSTRAINT cryptomoneys_pkey PRIMARY KEY (\"integer\")\n" +
                    ")";
            Statement statement = connection.createStatement();
            boolean result = statement.execute(_sqlText);

        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }


    public void setData(CryptoMoneyModel _model) {
        try {
            String _sqlText = "INSERT INTO cryptomoneys (symbol, price, insertdate) VALUES ('" + _model.symbol + "'," + _model.price + ",'" + java.time.LocalDateTime.now() +
                    "')";

            Statement statement = connection.createStatement();
            boolean result = statement.execute(_sqlText);

            if (!result) {
                System.out.println("INSERT OK ! - " + _model.symbol + " - " + _model.price + " - " + java.time.LocalDateTime.now());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
