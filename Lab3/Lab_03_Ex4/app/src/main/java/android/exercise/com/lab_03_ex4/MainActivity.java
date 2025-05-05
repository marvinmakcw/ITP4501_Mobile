package android.exercise.com.lab_03_ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    TextView selection;
    ListView list;
    String[] planet_array;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        planet_array = getResources().getStringArray(R.array.planet_array);
        setContentView(R.layout.activity_main);
        selection = findViewById(R.id.selection);
        list = findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, planet_array));
        list.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        selection.setText("Planet - " + planet_array[position]);
    }
}