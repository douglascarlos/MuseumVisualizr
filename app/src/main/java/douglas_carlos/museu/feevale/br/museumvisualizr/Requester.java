package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class Requester extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progress;
    private Context context;
    private String data;

    public Requester(Context context) {
        this.progress = new ProgressDialog(context);;
        this.context = context;
        this.syncVisits();
        Log.d("JSON", data);
        this.execute();
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
        try {

            data = toJsonArray(visits).toString();

        } catch (JSONException e) {
            e.printStackTrace();
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
            values.append(URLEncoder.encode(data,"UTF-8"));

            URL url = new URL("http://museumvisualizr.esy.es/sync/index.php");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
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
