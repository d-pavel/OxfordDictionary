package cz.utb.fai.oxforddictionary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OblibeneActivity extends AppCompatActivity {

    private static boolean loaded = false;
    public static String content = "";
    public static ArrayList<String> oblibenePolozky = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listViewFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oblibene);

        listViewFavourites = (ListView) findViewById(R.id.listViewFavourites);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, oblibenePolozky);
        listViewFavourites.setAdapter(adapter);

        listViewFavourites.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.remove(oblibenePolozky.get(position));
                Toast.makeText(OblibeneActivity.this, "Položka odstraněna.", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        if(listViewFavourites != null && loaded == false) {
            restoreFavourites(listViewFavourites);
            loaded = true;
        }
    }

    public static void addItem() {
        if (!"".equals(content)) {
            oblibenePolozky.add(content);
            content = "";
        }
    }

    public void restoreFavourites(View v) {
        SharedPreferences preferencesFav = getSharedPreferences("favourites", Context.MODE_PRIVATE);
        Set<String> fav = preferencesFav.getStringSet("favourites", new HashSet<String>());
        oblibenePolozky.addAll(fav);
    }
}
