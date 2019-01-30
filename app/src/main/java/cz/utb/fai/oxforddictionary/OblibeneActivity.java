package cz.utb.fai.oxforddictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
    }

    public static void addItem() {
        if (!"".equals(content)) {
            oblibenePolozky.add(content);
            content = "";
        }
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
