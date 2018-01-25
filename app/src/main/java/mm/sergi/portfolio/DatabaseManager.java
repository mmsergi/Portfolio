package mm.sergi.portfolio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sergi on 05/12/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String name = "coins.db";

    private static final int version = 1;

    private static final String createCoinsTable = "create table coins (" +
            " id text, name text, symbol text, rank int, price_usd float, price_btc float," +
            " volume_usd_24h float, market_cap_usd float, available_supply float, total_supply float, max_supply float," +
            " percent_change_1h float, percent_change_24_h float, percent_change_7d float, last_updated timestamp," +
            " price_curr float, volume_curr_24h float, market_cap_curr, image_url text)";


    public DatabaseManager(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createCoinsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists coins");
        onCreate(db);
    }

    public void insertCoin (Coin item){
        SQLiteDatabase db = getWritableDatabase();

        if (db!=null) {
            ContentValues values = new ContentValues();

            values.put("id", item.getId());
            values.put("name", item.getName());
            values.put("symbol", item.getSymbol());
            values.put("rank", item.getRank());
            values.put("price_usd", item.getPrice_usd());
            values.put("price_btc", item.getPrice_btc());
            values.put("volume_usd_24h", item.getVolume_usd_24h());
            values.put("market_cap_usd", item.getMarket_cap_usd());
            values.put("available_supply", item.getAvailable_supply());
            values.put("total_supply", item.getTotal_supply());
            values.put("max_supply", item.getMax_supply());
            values.put("percent_change_1h", item.getPercent_change_1h());
            values.put("percent_change_24_h", item.getPercent_change_24h());
            values.put("percent_change_7d", item.getPercent_change_7d());
            values.put("last_updated", item.getLast_updated());
            values.put("price_curr", item.getPrice_curr());
            values.put("volume_curr_24h", item.getVolume_curr_24h());
            values.put("market_cap_curr", item.getMarket_cap_curr());
            //values.put("image_url", AppController.getImageURL(item.getSymbol()));

            db.insert("coins", null, values);
            db.close();
        }
    }

    public void deleteCoinsTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table if exists coins");
        onCreate(db);
    }
}