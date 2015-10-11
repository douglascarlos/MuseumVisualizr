package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

    private final int REQUEST_CODE = 666;
    private TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.txt_result = (TextView) findViewById(R.id.txt_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void identifyKiosk(View view){
        try{
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.setPackage("com.google.zxing.client.android");
            this.startActivityForResult(intent, this.REQUEST_CODE);
        }catch (Exception e){
            Log.d("ERROR", e.getMessage());
            Toast.makeText(this, "Aviso: Você deve ter Barcode Scanner instalado no seu dispositivo!", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("BEGIN", "onActivityResult");

        if(resultCode == Activity.RESULT_OK){
            Log.d("DEBUG_1", ""+resultCode);

            String content = data.getStringExtra("SCAN_RESULT");
            Log.d("CONTENT", content);

            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            this.txt_result.setText(format + " - " + content);

            this.findKiosk(content);

        }

        Log.d("END", "onActivityResult");
    }

    public void findKiosk(String codeKiosk){
        Intent kioskIntent = new Intent(getBaseContext(), KioskActivity.class);
        kioskIntent.putExtra("codeKiosk", codeKiosk);
        startActivity(kioskIntent);
    }
}
