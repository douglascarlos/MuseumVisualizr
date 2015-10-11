package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class KioskActivity extends Activity {

    private int kioskIndex;
    private WebView webViewKiosk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

        webViewKiosk = (WebView) findViewById(R.id.webViewKiosk);

        String codeKiosk = (String) getIntent().getExtras().get("codeKiosk");
        Log.d("CODE", codeKiosk);

        int indexOfSearch = Kiosk.getIndexOf(codeKiosk);
        kioskIndex = indexOfSearch > -1 ? indexOfSearch : 0;
        loadKiosk(kioskIndex);
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

    public void loadKiosk(int index){
        webViewKiosk.loadUrl(Kiosk.getURLFrom(index));
    }
}
