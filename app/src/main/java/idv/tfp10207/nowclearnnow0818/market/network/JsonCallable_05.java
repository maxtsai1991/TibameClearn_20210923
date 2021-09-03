package idv.tfp10207.nowclearnnow0818.market.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

public class JsonCallable_05 implements Callable<String> {
    private final static String TAG = "TAG_JsonCallable";
    private final String url;
    private final String outStr;
    private final String apiKey;

    public JsonCallable_05(String url, String outStr, String apiKey) {
        this.url = url;
        this.outStr = outStr;
        this.apiKey = apiKey;
    }

    @Override
    public String call() {
        return getData();
    }

    private String getData() {
        HttpsURLConnection connection = null;
        StringBuilder inStr = new StringBuilder();
        try {
            connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setDoInput(true); // allow inputs
            connection.setDoOutput(true); // allow outputs
            // 不知道請求內容大小時可以呼叫此方法將請求內容分段傳輸，設定0代表使用預設大小
            connection.setChunkedStreamingMode(0);
            connection.setUseCaches(false); // do not use a cached copy
            connection.setRequestMethod("POST");
            // 加上Content-Type與x-api-key設定，否則錯誤
            // 參看https://docs.tappaysdk.com/google-pay/zh/back.html#pay-by-prime-api
            connection.setRequestProperty("Content-Type", "application/json"); //送至server要寫是什麼格式 (json)
            connection.setRequestProperty("x-api-key", apiKey); //標頭 "x-api-key"，值為 apiKey 在tp會先檢查標題的值，確認為會員，才會往下再執行
            connection.setRequestProperty("charset", "UTF-8"); // 編碼

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream()));
            bw.write(outStr);
            Log.d(TAG, "output: " + outStr);
            bw.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    inStr.append(line);
                }
            } else {
                Log.d(TAG, "response code: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        Log.d(TAG, "input: " + inStr);
        return inStr.toString();
    }
}
