package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import douglas_carlos.museu.feevale.br.museumvisualizr.utils.VisitAdapter;

public class VisitsActivity extends KioskHandlerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits);

        loadVisits();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_synced, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void loadVisits(){
        DBHelper helper = getDBHelper();
        helper.open();
        List<Visit> visits = Visit.all(helper);
        helper.close();

        VisitAdapter adapter = new VisitAdapter(this, visits);
        ListView listVisits = (ListView) findViewById(R.id.list_visits);
        listVisits.setAdapter(adapter);
    }

    public void syncVisits(View view){
        new Requester(this);
    }
}
