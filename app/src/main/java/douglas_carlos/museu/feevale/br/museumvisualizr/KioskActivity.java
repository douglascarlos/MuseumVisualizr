package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.IOException;

public class KioskActivity extends Activity {

    private WebView webViewKiosk;
    private KioskManager kioskManager;
    private int kioskIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

        webViewKiosk = (WebView) findViewById(R.id.webViewKiosk);

        String codeKiosk = (String) getIntent().getExtras().get("codeKiosk");
        Log.d("CODE", codeKiosk);

        try {
            kioskManager = new KioskManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        kioskIndex = kioskManager.getIndexOf(codeKiosk);
//        loadKiosk(kioskIndex);
        loadKiosk(codeKiosk);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kiosk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void loadKiosk(int index){
//        webViewKiosk.loadUrl(kioskManager.getURLFrom(index));
//    }

    public void loadKiosk(String str){
        webViewKiosk.loadUrl(kioskManager.getURLFrom(str));
    }
}
