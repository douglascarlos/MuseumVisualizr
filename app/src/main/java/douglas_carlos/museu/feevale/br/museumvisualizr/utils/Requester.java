package douglas_carlos.museu.feevale.br.museumvisualizr.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import douglas_carlos.museu.feevale.br.museumvisualizr.VisitsActivity;
import douglas_carlos.museu.feevale.br.museumvisualizr.utils.DBHelper;
import douglas_carlos.museu.feevale.br.museumvisualizr.utils.Visit;

public class Requester extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progress;
    private Context context;
    private String data;

    protected final static String SERVICE_DOMAIN = "museumvisualizr.esy.es";

    public Requester(Context context) {
        this.progress = new ProgressDialog(context);;
        this.context = context;
        this.syncVisits();
    }

    private static JSONArray toJsonArray(List<Visit> visits) throws JSONException {
        JSONArray json = new JSONArray();
        for (Visit visit : visits) json.put(visit.toJson());
        return json;
    }

    public void syncVisits(){

        DBHelper helper = new DBHelper(context);
        helper.open();

        List<Visit> visits = Visit.allNotSynced(helper);

        if(visits.size()>0) {
            try {

                data = toJsonArray(visits).toString();
                Visit.updateAsSynchronized(helper, visits);
                Log.d("JSON", data);
                this.execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(context, "As visitas já estão sincronizadas!", Toast.LENGTH_LONG).show();
        }
        helper.close();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.setMessage("Sincronizando...");
        progress.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{

            StringBuilder values = new StringBuilder();
            values.append(URLEncoder.encode("json", "UTF-8"));
            values.append("=");
            values.append(URLEncoder.encode(data, "UTF-8"));

            values.append("&");

            values.append(URLEncoder.encode("android_id", "UTF-8"));
            values.append("=");
            values.append(URLEncoder.encode(Settings.Secure.ANDROID_ID, "UTF-8"));
            Log.d("ANDROID_ID", Settings.Secure.ANDROID_ID);

            URL url = new URL("http://" + SERVICE_DOMAIN + "/sync.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            out.write(values.toString());
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.d("POST_DATA", values.toString());
            Log.d("CODE", conn.getResponseCode() + "");
            Log.d("IN", in.readLine());

            out.close();
            os.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progress.dismiss();
    }
}
