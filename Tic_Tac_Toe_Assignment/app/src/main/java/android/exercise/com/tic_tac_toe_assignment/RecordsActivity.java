package android.exercise.com.tic_tac_toe_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

public class RecordsActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String sql;
    Cursor cursor = null;
    //String[] columns = {"playDate", "playTime", "winningStatus", "duration"};
    //String dataStrHeader = String.format("%s, %s, %s, %s\n", "playDate", "playTime", "winningStatus", "duration");
    String dataStr[];

    int countRecord = 0, countWin = 0, countLose = 0, countDraw = 0;

    private ListView lvRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        lvRecords = findViewById(R.id.lvRecords);
        initialDB();


    }
    public void clickHandler (View view){
        Intent i = new Intent(this, PiechartActivity.class);
        i.putExtra("Win", countWin);
        i.putExtra("Lose", countLose);
        i.putExtra("Draw", countDraw);
        i.putExtra("Record", countRecord);
        startActivity(i);
    }
    private void initialDB() {
        try {
            // Create a database if it does not exist
            db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.tic_tac_toe_assignment/RecordsDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            cursor = db.rawQuery("SELECT * FROM GamesLog ORDER BY gameID DESC;", null);

            dataStr = new String[cursor.getCount()];
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String playDate = cursor.getString(Math.max(0, cursor.getColumnIndex("playDate")));
                String playTime = cursor.getString(Math.max(0, cursor.getColumnIndex("playTime")));
                int duration = cursor.getInt(Math.max(0, cursor.getColumnIndex("duration")));
                String winningStatus = cursor.getString(Math.max(0, cursor.getColumnIndex("winningStatus")));
                if (Objects.equals(winningStatus, "Win")) {
                    countWin++;
                } else if (Objects.equals(winningStatus, "Lose")) {
                    countLose++;
                } else {
                    countDraw++;
                }
                dataStr[countRecord] = playDate + ", " + playTime + ", " + winningStatus + ", " + duration + " sec";
                countRecord++;
            }
            Log.i("Record", "" + countRecord);
            Log.i("Win", "" + countWin);
            Log.i("Lose", "" + countLose);
            Log.i("Draw", "" + countDraw);

            lvRecords.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_record_new, R.id.tvListRecord, dataStr));
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}