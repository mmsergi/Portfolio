package mm.sergi.portfolio;

import android.app.Application;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sergi on 06/12/2017.
 */

public class AppController extends Application {

    static JSONObject jsonImagesUrl;

    @Override
    public void onCreate() {
        super.onCreate();

        fetchImages();

    }

    public static String getImageURL(String symbol){
        try {
            JSONObject jsonCoins = new JSONObject(jsonImagesUrl.toString());
            JSONObject jsonImage = new JSONObject(jsonCoins.get(symbol).toString());
            return jsonImage.get("ImageUrl").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    public void fetchImages(){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.cryptocompare.com/api/data/coinlist/").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String resp = response.body().string();
                    Log.e("response", resp);
                    JSONObject json = null;

                    try {
                        json = new JSONObject(resp);
                        jsonImagesUrl = new JSONObject(json.get("Data").toString());

                        Log.e("jsonData", jsonImagesUrl.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }}

            });
    }
}