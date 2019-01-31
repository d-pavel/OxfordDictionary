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

    public static String content = "";
    public static ArrayList<String> oblibenePolozky = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listViewFavourites;
    //Button pridatDoOblibenych;


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

        if(listViewFavourites != null) {
            restoreFavourites(listViewFavourites);
        }
    }

    public static void addItem() {
        if (!"".equals(content)) {
            oblibenePolozky.add(content);
            content = "";
        }
    }

    public void saveFavourites(View v)
    {
        SharedPreferences preferencesFav = getSharedPreferences("favourites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesFav.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(oblibenePolozky);
        editor.putStringSet("favourites", set);
        editor.commit();

        Toast.makeText(this, "Data uložena!", Toast.LENGTH_SHORT).show();
        //Log.d("OOOO", "Result is: " + String.valueOf(preferencesFav.getString("favourites", set.toString())));
    }

    public void restoreFavourites(View v)
    {
        SharedPreferences preferencesFav = getSharedPreferences("favourites", Context.MODE_PRIVATE);

        Set<String> fav = preferencesFav.getStringSet("favourites", new HashSet<String>());
        oblibenePolozky.addAll(fav);

        Toast.makeText(this, "Data obnovena!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveFavourites(listViewFavourites);
    }

    /*protected void addOrDeleteItem(final Intent intent)//(String slovo, String definice, String veta)
    {
        listViewFavourites = (ListView) findViewById(R.id.listViewFavourites);
        //final EditText slovo = (EditText) findViewById(R.id.hledaneSlovo);
        pridatDoOblibenych = (Button) findViewById(R.id.button_AddToFavourites);

        oblibenePolozky = new ArrayList<>();
        adapter = new ArrayAdapter<String>(OblibeneActivity.this, android.R.layout.simple_list_item_multiple_choice, oblibenePolozky);

        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oblibenePolozky.add(intent.getData().toString());
                adapter.notifyDataSetChanged();
            }
        };

        listViewFavourites.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray positionChecker = listViewFavourites.getCheckedItemPositions();
                int count = listViewFavourites.getCount();

                for(int item = count - 1; item >= 0; item--)
                {
                    adapter.remove(oblibenePolozky.get(item));
                    Toast.makeText(OblibeneActivity.this, "Slovo bylo smazáno.", Toast.LENGTH_SHORT).show();
                }

                positionChecker.clear();
                adapter.notifyDataSetChanged();

                return false;
            }
        });

        pridatDoOblibenych.setOnClickListener(addListener);

        listViewFavourites.setAdapter(adapter);
    }*/
}
