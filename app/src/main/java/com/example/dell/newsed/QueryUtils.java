package com.example.dell.newsed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QueryUtils{
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }
    public static List<NewsFeatures> fetchNews (String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
           jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){

        }
  return extractNews(jsonResponse);
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
         String jsonResponse = "";
         if(url==null){
             return jsonResponse;
         }

          HttpURLConnection urlConnection = null;
          InputStream inputStream = null;
          try
          {
             urlConnection = (HttpURLConnection) url.openConnection();
             urlConnection.setRequestMethod("GET");
             urlConnection.setReadTimeout(10000);
             urlConnection.setConnectTimeout(15000);
             urlConnection.connect();

             if(urlConnection.getResponseCode()==200){
                 inputStream = urlConnection.getInputStream();
                 jsonResponse = readFromStream(inputStream);

             }
             else{
                 Log.e(LOG_TAG, "Error response code  "+urlConnection.getResponseCode() );
             }
          }
          catch (IOException e){
              e.printStackTrace();
      }
      finally {
              if(urlConnection != null){
                  urlConnection.disconnect();
              }
              if(inputStream!=null){
                  inputStream.close();
              }
          }
          return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
             while(line!=null){
                 stringBuilder.append(line);
                 line = bufferedReader.readLine();
             }

        }
        return stringBuilder.toString();
    }
    private static List<NewsFeatures> extractNews(String newsJson){
        if(TextUtils.isEmpty(newsJson)){
            return null;
        }
        List<NewsFeatures> newsFeatures = new ArrayList<>();
        try
        {
            JSONObject baseJsonResponse = new JSONObject(newsJson);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            String status = response.getString("status");
            if(status.equals("ok")) {

                JSONArray newsArray = response.getJSONArray("results");
                for (int i = 0; i < newsArray.length(); i++) {
                    JSONObject currentNews = newsArray.getJSONObject(i);
                    JSONObject fields = currentNews.getJSONObject("fields");
                    String headline = fields.getString("headline");
                    URL url =createUrl(fields.getString("shortUrl"));
                    URL imageUrl = createUrl(fields.getString("thumbnail"));
                    String dateTime = currentNews.getString("webPublicationDate");
                    JSONArray tags = currentNews.getJSONArray("tags");

                    //Downloading image from image url
                    Bitmap image = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                    // Combining author and publication
                    String author = "";
                    String SEPARATOR = " | ";
                    if (!tags.isNull(0)) {
                        JSONObject contributor = tags.getJSONObject(0);
                        author = contributor.getString("webTitle");
                    }
                    if (!tags.isNull(1)) {
                        JSONObject publication = tags.getJSONObject(1);
                        author = author + SEPARATOR + publication.getString("webTitle");
                    }

                    //Formatting date
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
                    Date date = dateFormat.parse(dateTime);
                    String formattedDate = new SimpleDateFormat("MMM dd, yyyy",
                            Locale.getDefault()).format(date);

                    NewsFeatures newsObject = new NewsFeatures(headline,url,image, author, formattedDate);
                    newsFeatures.add(newsObject);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

return newsFeatures;
    }


}

