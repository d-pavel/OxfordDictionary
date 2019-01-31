package cz.utb.fai.oxforddictionary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    String url;
    TextView vyhledanaData;
    TextView priklady;
    EditText hledaneSlovo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hledaneSlovo = findViewById(R.id.hledaneSlovo);
        vyhledanaData = findViewById(R.id.vyhledanaData);
        priklady = findViewById(R.id.priklady);

    }

    public void showHistoryButtonClick(View v)
    {
        Intent historyIntent = new Intent(this, HistorieActivity.class);
        historyIntent.putExtra("slovo", hledaneSlovo.getText().toString());
        historyIntent.putExtra("definice", vyhledanaData.getText().toString());
        historyIntent.putExtra("priklad", priklady.getText().toString());
        startActivity(historyIntent);
    }

    public void showFavouritesButtonClick(View v)
    {
        Intent favouritesActivity = new Intent(this, OblibeneActivity.class);
        startActivity(favouritesActivity);
    }

    public void requestApiButtonClick(View v)
    {
        APIDictionaryRequest dictionaryRequest = new APIDictionaryRequest(this, vyhledanaData, priklady, hledaneSlovo.getText().toString());
        url = dictionaryEntries();
        dictionaryRequest.execute(url);
        //HistorieActivity hist = new HistorieActivity();
        //hist.saveHistory(hist.findViewById(R.id.historieHledani));
    }

    public void addWordToFavouritesButtonClick(View v)
    {
        /*OblibeneActivity oblibene = new OblibeneActivity();
        Intent oblibeneIntent = new Intent(this, OblibeneActivity.class);
        oblibeneIntent.putExtra("slovo", hledaneSlovo.getText().toString());
        oblibeneIntent.putExtra("definice", vyhledanaData.getText().toString());
        oblibeneIntent.putExtra("priklad", priklady.getText().toString());
        oblibene.addOrDeleteItem(oblibeneIntent);
        startActivity(oblibeneIntent);*/
        OblibeneActivity.addItem();
    }

    private String dictionaryEntries()
    {
        final String language = "en";
        final String word = hledaneSlovo.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}
