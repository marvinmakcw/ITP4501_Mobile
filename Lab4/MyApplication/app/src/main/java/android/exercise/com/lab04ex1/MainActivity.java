package android.exercise.com.lab04ex1;

import static android.provider.ContactsContract.Intents.Insert.NOTES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Button btnClose;
    EditText data;
    CheckBox sdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClose = findViewById(R.id.btnClose);
        data = findViewById(R.id.txtData);
        sdCard = findViewById(R.id.cbSDCard);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean prefSDCard = settings.getBoolean("prefSDCard", false);
        sdCard.setChecked(prefSDCard);

        verifyStoragePermissions(this);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        //editor.putBoolean("prefSDCard", );

        editor.commit();
    }


    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.btnWriteSDFile:
                if (sdCard.isChecked()) {
                    try {
                        File myFile = new File("/sdcard/mysdfile.txt");
                        myFile.createNewFile();
                        FileOutputStream fOut = new FileOutputStream(myFile);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(data.getText());
                        myOutWriter.close();
                        fOut.close();
                        Toast.makeText(getBaseContext(), "Writing SD - mysdfile.txt", Toast.LENGTH_SHORT).show();
                        System.out.println("Writing SD - mysdfile.txt");
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(openFileOutput(NOTES, 0));
                        out.write(data.getText().toString());
                        out.close();
                        Toast.makeText(getBaseContext(), "Writing file - Notes.txt", Toast.LENGTH_SHORT).show();
                        System.out.println("Writing file - Notes.txt");
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e);
                    }
                }
                break;
            case R.id.btnReadSDFile:
                if (sdCard.isChecked()) {
                    try {
                        File myFile = new File("/sdcard/mysdfile.txt");
                        FileInputStream fIn = new FileInputStream(myFile);
                        InputStreamReader tmp = new InputStreamReader(fIn);
                        BufferedReader reader = new BufferedReader(tmp);
                        String str;
                        StringBuffer buf = new StringBuffer();
                        while ((str = reader.readLine()) != null) {
                            buf.append(str + "\n");
                        }
                        fIn.close();
                        data.setText(buf.toString());
                        Toast.makeText(getBaseContext(), "Reading SD - mysdfile.txt", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e);
                    }
                } else {
                    try {
                        InputStream in = openFileInput(NOTES);
                        if (in != null) {
                            InputStreamReader tmp = new InputStreamReader(in);
                            BufferedReader reader = new BufferedReader(tmp);
                            String str;
                            StringBuffer buf = new StringBuffer();
                            while ((str = reader.readLine()) != null) {
                                buf.append(str + "\n");
                            }
                            in.close();
                            data.setText(buf.toString());
                            Toast.makeText(getBaseContext(), "Reading file - Notes.txt", Toast.LENGTH_SHORT).show();
                        } else {
                            //
                        }
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e);
                    }
                }
                break;
            case R.id.btnClearScreen:
                data.setText("");
                break;

            case R.id.btnClose:
                finish();
                break;
        }
    }
}