package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;

import douglas_carlos.museu.feevale.br.museumvisualizr.utils.DBHelper;
import douglas_carlos.museu.feevale.br.museumvisualizr.utils.KioskManager;

public abstract class KioskHandlerActivity extends AppCompatActivity {

    protected KioskManager kioskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            this.kioskManager = new KioskManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findKiosk(String codeKiosk){
        if(kioskManager.has(codeKiosk))
            this.loadKioskPage(codeKiosk);
        else {
            String strMsg = "O quiosque \"" + codeKiosk + "\" não foi encontrado!";
            Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
        }
    }

    protected void loadKioskPage(String codeKiosk) {
        Intent kioskIntent = new Intent(getBaseContext(), KioskActivity.class);
        kioskIntent.putExtra("codeKiosk", codeKiosk);
        startActivity(kioskIntent);
    }

    protected DBHelper getDBHelper(){
        return new DBHelper(this);
    }

}
