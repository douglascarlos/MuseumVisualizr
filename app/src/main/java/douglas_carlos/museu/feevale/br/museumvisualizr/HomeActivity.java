package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends Activity {

    private final int REQUEST_CODE = 666;
    private KioskManager kioskManager;

    private TextView txtVisitor;
    static final String PREF_VISITOR = "Preferencias";
    static final String VISITOR_NAME = "NOME";
    String nameVisitor;
    SharedPreferences shPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            this.kioskManager = new KioskManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        shPref = getSharedPreferences(PREF_VISITOR, 0);
        nameVisitor = shPref.getString(VISITOR_NAME, "Sem Nome!");
        txtVisitor = (TextView) findViewById(R.id.txtVisitor);
        txtVisitor.setText(nameVisitor);
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
            Log.d("DEBUG_1", "" + resultCode);

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

    public void saveVisitor(View view){
        SharedPreferences.Editor editor = shPref.edit();
        editor.putString(VISITOR_NAME, txtVisitor.getText().toString());
        nameVisitor = txtVisitor.getText().toString();
        editor.commit();
        Toast.makeText(this, nameVisitor + " salvo como visitante titular!", Toast.LENGTH_LONG).show();
    }

}
