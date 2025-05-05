package android.exercise.com.lab5ex3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton ibImage;
    ImageButton ibEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibImage = findViewById(R.id.ibImage);
        ibEmail = findViewById(R.id.ibEmail);
        registerForContextMenu(ibImage);
        registerForContextMenu(ibEmail);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Edit");
        menu.add(0, 2, 2, "Exit");
        menu.add(0, 3, 3, "Undo");
        menu.add(0, 4, 4, "Help");
        menu.add(0, 5, 5, "Search");
        menu.add(0, 6, 6, "About");

        SubMenu smFile = menu.addSubMenu(0, 7, 7, "File");
        smFile.add(0, 7, 7, "Open");
        smFile.getItem(0).setIcon(R.drawable.ic_baseline_open_in_new_24);
        smFile.add(0, 8, 8, "Save");
        smFile.getItem(1).setIcon(R.drawable.ic_baseline_save_24);
        smFile.add(0, 9, 9, "Delete");
        smFile.getItem(2).setIcon(R.drawable.ic_baseline_delete_24);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return (applyMenuOption(item)) || super.onOptionsItemSelected(item);
    }

    private boolean applyMenuOption(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == 6) {
            openDialog();
            return true;
        }else if (menuItemId == 2) {
            finish();
        }
        return false;
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This is an Options Menu");
        builder.setTitle("About");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v == ibImage) {
            menu.add(0, 10, 1, "Open");
            menu.add(0, 11, 2, "Save");
            menu.add(0, 12, 3, "Delete");
        } else if (v == ibEmail) {
            menu.add(0, 13, 1, "Send");
            menu.add(0, 14, 2, "Read");
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        return (applyMenuOption(item) ||
                super.onContextItemSelected(item));
    }
}