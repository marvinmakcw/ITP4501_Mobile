package ict.mobile;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
	SQLiteDatabase db;
	String sql;
	Cursor cursor = null;
	String[] columns = { "Mid", "Name", "Password", "Age" };
	TextView tvData;
	String dataStr = String.format("%4d %10s %7s %2d\n", "Mid", "Name", "Password", "Age");
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


		try {
			// Create a database if it does not exist
			db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.lab04_ex3", null, SQLiteDatabase.CREATE_IF_NECESSARY);

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
			Toast.makeText(this, "Table Seller is created and initialised.",
					Toast.LENGTH_SHORT).show();

			cursor = db.rawQuery("select * from Member order by Mid", null);
			dataStr = "";
			while (cursor.moveToNext()) {
				int Mid = cursor.getInt(cursor.getColumnIndex("Mid"));
				String Name = cursor.getString(cursor.getColumnIndex("Name"));
				String Password = cursor.getString(cursor.getColumnIndex("Password"));
				String Age = cursor.getInt(cursor.getColumnIndex("Age"));
				dataStr += String.format("%4d %10s %4s %2d\n", Mid, Name, Password, Age);
			}
			tvData.setText(dataStr);

			db.close();
		} catch (SQLiteException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View view) {

	}
}