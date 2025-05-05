package android.exercise.com.tic_tac_toe_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String sql;
    Cursor cursor = null;
    //String[] columns = {"playDate", "playTime", "winningStatus", "duration"};
    TextView tvData;
    //String dataStrHeader = String.format("%s, %s, %s, %s\n", "playDate", "playTime", "winningStatus", "duration");
    String dataStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout linearLayout = findViewById(R.id.mainLayout);
        //AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        //animationDrawable.setEnterFadeDuration(2500);
        //animationDrawable.setExitFadeDuration(5000);
        //animationDrawable.start();

        initialDB();
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnRanking = (Button)findViewById(R.id.btnRanking);
        Button btnRecords = (Button)findViewById(R.id.btnRecords);
        Button btnClose = (Button)findViewById(R.id.btnClose);
        final MediaPlayer BUTTON_PRESSED = MediaPlayer.create(this, R.raw.button_pressed);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUTTON_PRESSED.start();
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUTTON_PRESSED.start();
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
            }
        });

        btnRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUTTON_PRESSED.start();
                startActivity(new Intent(MainActivity.this, RecordsActivity.class));
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUTTON_PRESSED.start();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    private void initialDB() {
        try {
            // Create a database if it does not exist
            db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.tic_tac_toe_assignment/RecordsDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //sql = "DROP TABLE IF EXISTS GamesLog;";
            //db.execSQL(sql);
            sql = "CREATE TABLE IF NOT EXISTS GamesLog (gameID INTEGER PRIMARY KEY AUTOINCREMENT, playDate text, playTime text, duration INTEGER, winningStatus text);";
            db.execSQL(sql);

            //cursor = db.rawQuery("SELECT * FROM GamesLog", null);
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
