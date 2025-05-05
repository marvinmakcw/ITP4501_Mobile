package android.exercise.com.lab06ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnGo;
    private TextView tvResult, tvUrl;
    private ListView lvCode;
    private String[] listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.buttonGo);
        tvResult = findViewById(R.id.textViewResult);
        tvUrl = findViewById(R.id.textViewUrl);
        lvCode = findViewById(R.id.listViewCode);

        btnGo.setOnClickListener(this);
        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);
    }

    @Override
    public void onClick(View view) {
        InputStream inputStream = null;
        String result = "";

        URL url = null;

        try {
            url = new URL(tvUrl.getText().toString());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Make GET request
            con.setRequestMethod("GET");  // May omit this line since "GET" is the default.
            con.connect();

            // Get response string from inputstream of the connection

            inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();

            JSONObject json = new JSONObject(result);
            JSONArray codeArray = json.getJSONArray("campuses");
            listItems = new String[codeArray.length()];
            for (int i = 0; i < codeArray.length(); i++) {
                listItems[i] = codeArray.getJSONObject(i).getString("code");
            }

            lvCode.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));
            lvCode.setOnItemClickListener(this);

        } catch (Exception e) {
            tvResult.setText(e.toString());
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tvResult.setText(listItems[i] + " selected.");
    }
}