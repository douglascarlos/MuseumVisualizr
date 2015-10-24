package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends Activity {

    private final int REQUEST_CODE = 666;
    private KioskManager kioskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            this.kioskManager = new KioskManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            this.findKiosk(content);
        }

        Log.d("END", "onActivityResult");
    }

    public void findKiosk(String codeKiosk){
        if(kioskManager.has(codeKiosk))
            this.loadKioskPage(codeKiosk);
        else {
            String strMsg = "O quiosque \"" + codeKiosk + "\" não foi encontrado!";
            Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
        }
    }

    private void loadKioskPage(String codeKiosk) {
        Intent kioskIntent = new Intent(getBaseContext(), KioskActivity.class);
        kioskIntent.putExtra("codeKiosk", codeKiosk);
        startActivity(kioskIntent);
    }

//    public void onIdentifyManually(View v){
//        TextView editText = (TextView) findViewById(R.id.editText);
//        this.findKiosk(editText.getText().toString());
//    }
}
