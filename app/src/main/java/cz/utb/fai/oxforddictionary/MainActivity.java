package cz.utb.fai.oxforddictionary;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    /*public void Pridej()
    {
        HistorieActivity hist = new HistorieActivity();
        hist.VlozText(hledaneSlovo.getText().toString(),vyhledanaData.getText().toString(), priklady.getText().toString());
    }*/

    public void showHistory(View v)
    {
        //Intent historyActivity = new Intent(this, HistorieActivity.class);
        Intent historyIntent = new Intent(this, HistorieActivity.class);
        historyIntent.putExtra("slovo", hledaneSlovo.getText().toString() );
        historyIntent.putExtra("definice", vyhledanaData.getText().toString());
        historyIntent.putExtra("priklad", priklady.getText().toString());
        startActivity(historyIntent);
        //startActivity(historyActivity);
    }

    public void showFavourites(View v)
    {
        Intent favouritesActivity = new Intent(this, OblibeneActivity.class);
        startActivity(favouritesActivity);
    }

    public void requestApiButtonClick(View v)
    {
        APIDictionaryRequest dictionaryRequest = new APIDictionaryRequest(this, vyhledanaData, priklady, hledaneSlovo.getText().toString());
        url = dictionaryEntries();
        dictionaryRequest.execute(url);



        //HistorieActivity addWordToHistory = new HistorieActivity();
        //addWordToHistory.VlozText(hledaneSlovo.getText().toString(),vyhledanaData.getText().toString(), priklady.getText().toString());
    }

    private String dictionaryEntries()
    {
        final String language = "en";
        final String word = hledaneSlovo.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}
