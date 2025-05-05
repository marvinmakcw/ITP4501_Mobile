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

public class DatabaseQuery extends Activity implements OnClickListener {
	SQLiteDatabase db;
	String sql;
	Cursor cursor = null;
	String[] columns = { "sID", "sPassword", "sName", "sGender" };
	TextView tvData;
	String dataStr = String.format("%4s %-10s %7s\n", "sID", "sName", "sGender");
	RadioButton rbSID, rbSName, rbAsc, rbDesc;
	Button btnShow;

	public void findView() {
		tvData = findViewById(R.id.data);
		rbSID = findViewById(R.id.rb_sID);
		rbSName = findViewById(R.id.rb_sName);
		rbAsc = findViewById(R.id.rb_asc);
		rbDesc = findViewById(R.id.rb_desc);
		btnShow = findViewById(R.id.btn_show);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findView();
		
		/* btnShow registers the click listener */
		btnShow.setOnClickListener(this);
		

		try {
			// Create a database if it does not exist
			db = SQLiteDatabase.openDatabase("/data/data/ict.mobile/eBidDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

			sql = "DROP TABLE IF EXISTS Seller;";
			db.execSQL(sql);

			sql = "CREATE TABLE Seller (sID int PRIMARY KEY, sPassword text, sName text, sGender text);";
			db.execSQL(sql);

			db.execSQL("INSERT INTO Seller(sID, sPassword, sName, sGender) values"
							+ "(1001, 'pswd1001', 'Susan', 'F'); ");
			db.execSQL("INSERT INTO Seller(sID, sPassword, sName, sGender) values"
							+ "(1002, 'pswd1002', 'Peter', 'M'); ");
			db.execSQL("INSERT INTO Seller(sID, sPassword, sName, sGender) values"
							+ "(1003, 'pswd1003', 'Wendy', 'F'); ");
			db.execSQL("INSERT INTO Seller(sID, sPassword, sName, sGender) values"
							+ "(1004, 'pswd1004', 'Mandy', 'F'); ");
			db.execSQL("INSERT INTO Seller(sID, sPassword, sName, sGender) values"
							+ "(1005, 'pswd1005', 'Josephine', 'F'); ");
			Toast.makeText(this, "Table Seller is created and initialised.",
					Toast.LENGTH_SHORT).show();

			cursor = db.rawQuery("select * from Seller order by sName", null);
			dataStr = "";
			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor.getColumnIndex("sID"));
				String name = cursor.getString(cursor.getColumnIndex("sName"));
				String gender = cursor.getString(cursor.getColumnIndex("sGender"));
				dataStr += String.format("%4d %-10s %4s\n", id, name, gender);
			}
			tvData.setText(dataStr);
			
			db.close();
		} catch (SQLiteException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}


	public void onClick(View v) {
		if (v.equals(btnShow)) {
			if(rbSID.isChecked() && rbAsc.isChecked()){
				db = SQLiteDatabase.openDatabase("/data/data/ict.mobile/eBidDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

				cursor = db.rawQuery("select * from Seller order by sID", null);
				dataStr = "";
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex("sID"));
					String name = cursor.getString(cursor.getColumnIndex("sName"));
					String gender = cursor.getString(cursor.getColumnIndex("sGender"));
					dataStr += String.format("%4d %-10s %4s\n", id, name, gender);
				}
				tvData.setText(dataStr);

				db.close();
			}

			if(rbSID.isChecked() && rbDesc.isChecked()){
				db = SQLiteDatabase.openDatabase("/data/data/ict.mobile/eBidDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

				cursor = db.rawQuery("select * from Seller order by sID Desc", null);
				dataStr = "";
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex("sID"));
					String name = cursor.getString(cursor.getColumnIndex("sName"));
					String gender = cursor.getString(cursor.getColumnIndex("sGender"));
					dataStr += String.format("%4d %-10s %4s\n", id, name, gender);
				}
				tvData.setText(dataStr);

				db.close();
			}

			if(rbSName.isChecked() && rbAsc.isChecked()){
				db = SQLiteDatabase.openDatabase("/data/data/ict.mobile/eBidDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

				cursor = db.rawQuery("select * from Seller order by sName", null);
				dataStr = "";
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex("sID"));
					String name = cursor.getString(cursor.getColumnIndex("sName"));
					String gender = cursor.getString(cursor.getColumnIndex("sGender"));
					dataStr += String.format("%4d %-10s %4s\n", id, name, gender);
				}
				tvData.setText(dataStr);

				db.close();
			}

			if(rbSName.isChecked() && rbDesc.isChecked()){
				db = SQLiteDatabase.openDatabase("/data/data/ict.mobile/eBidDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

				cursor = db.rawQuery("select * from Seller order by sName Desc", null);
				dataStr = "";
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex("sID"));
					String name = cursor.getString(cursor.getColumnIndex("sName"));
					String gender = cursor.getString(cursor.getColumnIndex("sGender"));
					dataStr += String.format("%4d %-10s %4s\n", id, name, gender);
				}
				tvData.setText(dataStr);

				db.close();
			}
		}
	}

}