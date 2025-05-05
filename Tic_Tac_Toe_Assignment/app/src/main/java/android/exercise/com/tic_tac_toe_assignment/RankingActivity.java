package android.exercise.com.tic_tac_toe_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    private ListView lvRanking;
    private String[] listRanking;
    String api = "http://10.0.2.2:81/ranking_api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        lvRanking = findViewById(R.id.lvRanking);
        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        InputStream inputStream;
        String result = "";

        URL url;


        try {
            url = new URL(api);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Make GET request
            con.setRequestMethod("GET");  // May omit this line since "GET" is the default.
            con.connect();

            // Get response string from inputstream of the connection

            inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();

            JSONArray rankArray = new JSONArray(result);
            JSONArray sortedRankArray = new JSONArray();

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < rankArray.length(); i++) {
                jsonValues.add(rankArray.getJSONObject(i));
            }
            Collections.sort(jsonValues, new Comparator<JSONObject>() {
                private static final String KEY_NAME = "Duration";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    int durationA = 0, durationB = 0;
                    try {
                        durationA = (int) a.get(KEY_NAME);
                        durationB = (int) b.get(KEY_NAME);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return durationA- durationB;
                }
            });

            for (int i = 0; i < rankArray.length(); i++) {
                sortedRankArray.put(jsonValues.get(i));
            }

            listRanking = new String[sortedRankArray.length()];

            for (int i = 0; i < rankArray.length(); i++) {
                listRanking[i] = "Rank " + (i + 1) + " , " + sortedRankArray.getJSONObject(i).getString("Name") + ", " + sortedRankArray.getJSONObject(i).getString("Duration") + " sec";
            }

            lvRanking.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_ranking_new, R.id.tvListRanking, listRanking));

        } catch (Exception e) {
            Log.i("error", "" + e);
        }
    }

}