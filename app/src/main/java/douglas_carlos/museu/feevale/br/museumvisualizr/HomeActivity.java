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

import douglas_carlos.museu.feevale.br.museumvisualizr.utils.UserHelper;

public class HomeActivity extends KioskHandlerActivity {

    private final int REQUEST_CODE = 666;

    private TextView txtVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String nameVisitor = UserHelper.get(this);
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
        Intent intent;

        switch(id){
            case R.id.action_text_search:
                intent = new Intent(getBaseContext(), TextSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_view_visits:
                intent = new Intent(getBaseContext(), VisitsActivity.class);
                startActivity(intent);
                break;
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
            Log.d("DEBUG_1", "" + resultCode);

            String content = data.getStringExtra("SCAN_RESULT");
            Log.d("CONTENT", content);

            this.findKiosk(content);
        }

        Log.d("END", "onActivityResult");
    }

    public void saveVisitor(View view){
        String nameVisitor = txtVisitor.getText().toString();
        UserHelper.set(nameVisitor);
        Toast.makeText(this, nameVisitor + " salvo como visitante titular!", Toast.LENGTH_LONG).show();
    }

}
