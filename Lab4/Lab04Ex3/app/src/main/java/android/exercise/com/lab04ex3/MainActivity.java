package android.exercise.com.lab04ex3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    SQLiteDatabase db;
    String sql;
    Cursor cursor = null;
    String[] columns = { "Mid", "Name", "Password", "Age" };
    TextView tvData;
    String dataStrHeader = String.format("%4s %-12s %-9s %3s\n", "Mid", "Name", "Password", "Age");
    String dataStr;
    Button btnDBinitial, btnQuery, btnAction;

    public void findView() {
        tvData = findViewById(R.id.data);
        btnDBinitial = findViewById(R.id.btnDBinitial);
        btnQuery = findViewById(R.id.btnQuery);
        btnAction = findViewById(R.id.btnAction);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        btnDBinitial.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnAction.setOnClickListener(this);
        initialDB();
    }

    private void initialDB() {
        try {
            // Create a database if it does not exist
            db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.lab04ex3/MemberDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            sql = "DROP TABLE IF EXISTS Member;";
            db.execSQL(sql);
            sql = "CREATE TABLE Member (Mid int PRIMARY KEY, Name text, Password text, Age int);";
            db.execSQL(sql);

            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1001, 'Amy Carl', '12345', 16); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1002, 'Helen Leung', '88888', 25); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1003, 'Robert Chan', 'iloveu', 61); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1004, 'Carol Wong', 'peterpan', 33); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1005, 'Carman Wong', 'pooh', 44); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1006, 'John Chan', 'johnchan', 28); ");
            db.execSQL("INSERT INTO Member(Mid, Name, Password, Age) values"
                    + "(1007, 'Paul Lam', 'apple', 16); ");
            Toast.makeText(this, "Table Member is created and initialised.",
                    Toast.LENGTH_SHORT).show();

            cursor = db.rawQuery("select * from Member", null);
            setDataStr();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void setDataStr() {
        dataStr = dataStrHeader;
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int mid = cursor.getInt(Math.max(0,cursor.getColumnIndex("Mid")));
            String name = cursor.getString(Math.max(0,cursor.getColumnIndex("Name")));
            String password = cursor.getString(Math.max(0,cursor.getColumnIndex("Password")));
            int age = cursor.getInt(Math.max(0,cursor.getColumnIndex("Age")));
            dataStr += String.format("%4d %-12s %-9s %3d\n", mid, name, password, age);
        }
        tvData.setText(dataStr);
        db.close();
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnDBinitial)) {
            initialDB();
        }else if (view.equals(btnQuery)) {
            try {
                db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.lab04ex3/MemberDB", null, SQLiteDatabase.OPEN_READONLY);
                cursor = db.rawQuery("select * from Member where length(password) < 7 AND age > 20", null);
                setDataStr();
            }catch (SQLiteException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else if (view.equals(btnAction)) {
            try {
                db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.lab04ex3/MemberDB", null, SQLiteDatabase.OPEN_READWRITE);
                sql = "insert into Member values " + "(1008, 'Ronald Tang', 'ilovehk', 66)";
                db.execSQL(sql);

                sql = "update member set password = 'winnie' where mid = 1005";
                db.execSQL(sql);

                sql = "delete from Member where mid = 1003";
                db.execSQL(sql);

                cursor = db.query("Member", columns, null, null, null, null, null);
                setDataStr();
            }catch (SQLiteException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}