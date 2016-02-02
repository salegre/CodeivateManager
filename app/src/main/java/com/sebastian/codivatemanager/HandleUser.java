package com.sebastian.codivatemanager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sebastian on 31/01/2016.
 */
public class HandleUser {

    private final OkHttpClient client = new OkHttpClient();
    private String mJsonData;

    public String getJsonData() {
        return mJsonData;
    }

    //Request JSONData from website
    public void setJsonData(String user, final String fileName) throws Exception {
        Request request = new Request.Builder()
                .url("http://codeivate.com/users/" + user + ".json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                mJsonData = response.body().string();
            }
        });
    }

//    // Adds to file in the Internal Storage
//    private void addToFile(String jData, String fileName) {
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
//            outputStream.write(jData.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
