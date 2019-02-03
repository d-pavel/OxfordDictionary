package cz.utb.fai.oxforddictionary;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ClosingService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        SharedPreferences preferencesFav = getSharedPreferences("favourites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorFav = preferencesFav.edit();
        Set<String> favourites = new HashSet<String>();
        favourites.addAll(OblibeneActivity.oblibenePolozky);
        editorFav.putStringSet("favourites", favourites);
        editorFav.commit();

        SharedPreferences preferencesHis = getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorHis = preferencesHis.edit();
        Set<String> history = new HashSet<String>();
        history.addAll(HistorieActivity.historyItems);
        editorHis.putStringSet("history", history);
        editorHis.commit();

        stopSelf();
    }
}