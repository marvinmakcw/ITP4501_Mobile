package android.exercise.com.lab5ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] items = {"Send SMS", "Search on Google", "Wiktionary", "Wikipedia", "Show Map", "Show Street View", "Show Contact"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:93479123"));
                intent.putExtra("sms_body", "my message\n\"Testing\"");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, "IVE");
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wiktionary.org/wiki/mobile") );
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Android_(operating_system)") );
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:22.3199,114.036?z=17)") );
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=22.3192,114.1768&cbp=1,200,,0,1") );
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/") );
                startActivity(intent);
                break;
        }
    }
}