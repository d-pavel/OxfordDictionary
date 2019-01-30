package cz.utb.fai.oxforddictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class APIDictionaryRequest extends AsyncTask<String, Integer, String>
{
    // MY API Credentials
    final String app_id = "0acb2e39";
    final String app_key = "3926f2b76b9ffcc3cdade01e2795e808";

    String myURl;
    Context context;
    TextView vyhledanaData;
    TextView priklady;
    String word;

    public APIDictionaryRequest(Context context, TextView vyhledanaData, TextView priklady, String word)
    {
        this.context = context;
        this.vyhledanaData = vyhledanaData;
        this.priklady = priklady;
        this.word = word;
    }

    @Override
    protected String doInBackground(String... params)
    {
        myURl = params[0];
        try {
            URL url = new URL(myURl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

        String definition;
        String examples;
        try
        {
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");

            JSONObject lexicalEntries = results.getJSONObject(0);
            JSONArray laArray = lexicalEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject d = sensesArray.getJSONObject(0);
            JSONArray de = d.getJSONArray("definitions");

            definition = de.getString(0);

            vyhledanaData.setText(definition);
            //Toast.makeText(context, definition, Toast.LENGTH_SHORT).show();

            JSONArray ex = d.getJSONArray("examples");

            examples = ex.getString(0);

            int startIndex = examples.indexOf(":");
            int endIndex = examples.indexOf("}");
            String cut = examples.substring(startIndex + 2, endIndex - 1);
            //Log.d("MMMM", "Result is: " + String.valueOf(cut));
            //Toast.makeText(context, examples, Toast.LENGTH_SHORT).show();
            priklady.setText(cut);

            HistorieActivity.content += (word + "\n" + definition + "\n" + cut + "\n\n");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
}
