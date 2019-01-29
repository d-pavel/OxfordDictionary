package cz.utb.fai.oxforddictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    public void requestApiButtonClick(View v)
    {
        APIDictionaryRequest dictionaryRequest = new APIDictionaryRequest(this, vyhledanaData, priklady);
        url = dictionaryEntries();
        dictionaryRequest.execute(url);
    }

    private String dictionaryEntries()
    {
        final String language = "en";
        final String word = hledaneSlovo.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}
