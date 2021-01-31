# Nedir ?
Bu uygulama, spesifik kripto paraları belli zaman periyotlarıyla PostgreSQL veritabanına yazar, yazılan verileri ise yine belli periyotlarla api üzerinden kullanıcıya sunar.

## Kullanım

### PostgreSQL'e Veri Yazan Uygulama (cryptoserviceapp)
Uygulama, binance.com sitesinden alınan BTC, BNB ,ETH, XRP, BCH, LTC paralarını PostgreSQL'de veri tabanına 5 dakikada bir paranın fiyatını yazar.
Uygulama erişimi :<br/>
```cd {PATH}/binancecomdataproject/cryptoserviceapp/out/artifacts```<br/>
```java -jar cryptoserviceapp_jar```<br/>
Uygulamaya erişim için Setting.json dosyasından bağlantı bilgilerinin girilmesi gerekmektedir.<br/>

#### Sorgular
Tablo oluştur
```sql
CREATE TABLE public.cryptomoneys
(
    "integer" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    symbol character varying(30) COLLATE pg_catalog."default",
    insertdate timestamp(6) without time zone,
    price double precision,
    CONSTRAINT cryptomoneys_pkey PRIMARY KEY ("integer")
)
```
Kayıt Atma
```sql
INSERT INTO cryptomoneys (symbol, price, insertdate) VALUES ('symbol',price,'date')
```

### PostgreSQL'e Yazılan Verinin İstek ile Gösterilmesi (binanceapi-app)
Uygulama, veritabanına yazılan verilerin son 60 dakika ortalamasını ve son 15 dakikadaki değerlerinin 5'er dakikalık periyotlar ile JSON tipinde getirir.

URL'ler <br/>
``` 
localhost:8080/getPricesBySymbol?symbol={symbol} 
```  
<br/>

```json
[{"symbol":"ETHBTC","price":0.040069,"insertdate":"2021-01-30T16:49:40.859+00:00"},{"symbol":"ETHBTC","price":0.039673,"insertdate":"2021-01-30T13:55:17.294+00:00"},{"symbol":"ETHBTC","price":0.039682,"insertdate":"2021-01-30T13:55:11.867+00:00"}]
``` 

<br/>

``` 
localhost:8080/getPricesAvgBySymbol?symbol={symbol}
```
```json
{"symbol":"ETHBTC","price":0.03969525}
``` 

#### Sorgular

Son 15 dakinanın fiyatlarının 5'er dakikalık periyotlarla çekilmesi

```sql
select symbol,price,insertdate from cryptomoneys where symbol = '{symbol}'
ORDER BY insertdate desc limit 3 
```

Son 60 dakikanın değerlerinin ortalaması

```sql
select AVG(tablex.price) as price  from (select price from cryptomoneys where symbol = '{symbol}'
order by insertdate desc limit 12)tablex
```
