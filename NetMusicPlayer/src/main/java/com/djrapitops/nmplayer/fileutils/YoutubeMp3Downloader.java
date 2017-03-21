/*
 * https://stackoverflow.com/questions/12436584/youtube-to-mp3-conversion-using-web-site
 */
package com.djrapitops.nmplayer.fileutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author azujev, edited by Risto
 */
public class YoutubeMp3Downloader {

    public static String[] getLink(String url) throws ClientProtocolException, IOException {
        url = url.replace("https://www.youtube.com/watch?v=", "");
        url = url.replace("http://www.youtube.com/watch?v=", "");
        boolean passCode = false;
        String h = "";
        String title = "";
        String result = "";
        String[] returnVal = {"", ""};
        Map<String, String> jsonTable;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpInitialGet = new HttpGet("http://www.youtube-mp3.org/api/pushItem/?item=http%3A//www.youtube.com/watch%3Fv%3D" + url + "&xy=_");
        httpInitialGet.addHeader("Accept-Location", "*");
        httpInitialGet.addHeader("Referrer", "http://www.youtube-mp3.org");
        HttpParams params = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1");
        httpInitialGet.setParams(params);
        HttpResponse firstResponse = httpClient.execute(httpInitialGet);

        try {
            if (firstResponse.getStatusLine().toString().contains("200")) {
                passCode = true;
            }
        } finally {
            httpInitialGet.releaseConnection();
        }

        if (passCode) {
            while (true) {
                HttpGet httpStatusGet = new HttpGet("http://www.youtube-mp3.org/api/itemInfo/?video_id=" + url + "&adloc=");
                httpStatusGet.addHeader("Accept-Location", "*");
                httpStatusGet.addHeader("Referrer", "http://www.youtube-mp3.org");
                httpStatusGet.setParams(params);
                HttpResponse secondResponse = httpClient.execute(httpStatusGet);
                HttpEntity secondEntity = secondResponse.getEntity();
                InputStream is = secondEntity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    List<String> lines = reader.lines().collect(Collectors.toList());
                    lines.forEach((line) -> {
                        sb.append(line).append("\n");
                    });
                    is.close();
                    result = sb.toString();
                } catch (IOException e) {
                    ErrorManager.toLog(YoutubeMp3Downloader.class.getName(), e);
                }

                httpStatusGet.releaseConnection();
                result = result.replaceAll("\\}.*", "}");
                result = result.replaceAll(".*?\\{", "{");
                try {
                    JSONObject jsonData = new JSONObject(result);
                    JSONArray jsonArray = jsonData.names();
                    JSONArray valArray = jsonData.toJSONArray(jsonArray);
                    jsonTable = new HashMap<>(jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonTable.put(jsonArray.get(i).toString(), valArray.get(i).toString());
                    }
                    if (jsonTable.get("status").equals("serving")) {
                        h = jsonTable.get("h");
                        title = jsonTable.get("title");
                        break;
                    }
                } catch (JSONException e) {
                    ErrorManager.toLog(YoutubeMp3Downloader.class.getName(), e);
                }
            }
            returnVal[0] = "http://www.youtube-mp3.org/get?video_id=" + url + "&h=" + h;
            returnVal[1] = title;
            return returnVal;
        } else {
            ErrorManager.toLog(YoutubeMp3Downloader.class.getName(), new IllegalStateException("Video is not downloadable: " + url + " " + firstResponse.getStatusLine().toString()));
        }
        return null;
    }
}
