package cz.utb.fai.oxforddictionary;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HistorieActivity extends AppCompatActivity {

    private static boolean loaded = false;
    public static ArrayList<String> historyItems = new ArrayList<>();
    ListView historie;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historie);

        historie = (ListView) findViewById(R.id.historieHledani);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyItems);
        historie.setAdapter(adapter);


        if(loaded == false) {
            restoreHistory(historie);
            loaded = true;
        }
    }

    public void deleteAllFromHistoryButtonClick(View v) {
        historyItems.clear();
        adapter.clear();
    }

    public void restoreHistory(View v) {
        SharedPreferences preferencesFav = getSharedPreferences("history", Context.MODE_PRIVATE);
        Set<String> fav = preferencesFav.getStringSet("history", new HashSet<String>());
        historyItems.addAll(fav);
    }
}
