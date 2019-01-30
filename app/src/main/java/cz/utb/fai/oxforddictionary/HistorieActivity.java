package cz.utb.fai.oxforddictionary;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HistorieActivity extends AppCompatActivity {

    TextView historie;
    public static String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historie);
        historie = (TextView) findViewById(R.id.historieHledani);
        historie.append(content);
        historie.setMovementMethod(new ScrollingMovementMethod());
        //historie.append(content + historie.getText().toString());
        //VlozText();
    }

    public void deleteAll(View v)
    {
        content = "";
        historie.setText(content);
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
