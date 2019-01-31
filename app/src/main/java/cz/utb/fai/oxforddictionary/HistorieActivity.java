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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HistorieActivity extends AppCompatActivity {

    TextView historie;
    String textToSave = "";
    public static String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historie);


        //MainActivity.setDefaults("hist", historie.getText().toString(), this);
        historie = (TextView) findViewById(R.id.historieHledani);
        historie.append(content);

        if(historie != null) {
            restoreHistory(historie);
        }

        historie.setMovementMethod(new ScrollingMovementMethod());
        //historie.append(content + historie.getText().toString());
        //VlozText();
        //saveHistory(historie);
        //MainActivity.getDefaults("hist", this);
        //restoreHistory(historie);
    }

    public void deleteAllFromHistoryButtonClick(View v)
    {
        content = "";
        historie.setText(content);
    }

    public void saveHistory(View v)
    {
        SharedPreferences preferences = getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("historie", historie.getText().toString());
        editor.apply();

        Toast.makeText(this, "Data uložena!", Toast.LENGTH_SHORT).show();
        Log.d("MMMM", "Result is: " + String.valueOf(preferences.getString("historie", historie.getText().toString())));
    }

    public void restoreHistory(View v)
    {
        SharedPreferences preferences = getSharedPreferences("history", Context.MODE_PRIVATE);

        String hist = preferences.getString("historie", historie.getText().toString());
        historie.append(hist);

        Toast.makeText(this, "Data obnovena!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveHistory(historie);
    }

    /* public void VlozText()
    {
        //historie = (TextView) findViewById(R.id.historieHledani);

        Intent intent = getIntent();
        String slovo = intent.getStringExtra("slovo");
        String definice = intent.getStringExtra("definice");
        String priklad = intent.getStringExtra("priklad");
        //String text = (String) historie.getText();
        historie.append("\n\n" + slovo + "\n" + definice + "\n" + priklad);
    }*/

    /*public TextView addNewWordToHistory(String slovo, String priklady)
    {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(slovo + "\n" + priklady);
        return textView;
    }*/
}
