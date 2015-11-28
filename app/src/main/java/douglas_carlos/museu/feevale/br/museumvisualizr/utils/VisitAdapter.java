package douglas_carlos.museu.feevale.br.museumvisualizr.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import douglas_carlos.museu.feevale.br.museumvisualizr.R;
import douglas_carlos.museu.feevale.br.museumvisualizr.Visit;

public class VisitAdapter extends BaseAdapter{

    protected Context context;
    protected List<Visit> visits;

    public VisitAdapter(Context ctx, List<Visit> visits){
        this.context = ctx;
        this.visits = visits;
    }

    @Override
    public int getCount() {
        return visits.size();
    }

    @Override
    public Object getItem(int position) {
        return visits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return visits.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowVisit = inflater.inflate(R.layout.row_visit, parent, false);
        Visit visit = visits.get(position);

        TextView kioskName = (TextView) rowVisit.findViewById(R.id.kiosk_name);
        kioskName.setText(visit.getKioskCode());
        TextView visitor = (TextView) rowVisit.findViewById(R.id.visitor_name);
        visitor.setText(visit.getUserName());
        TextView date = (TextView) rowVisit.findViewById(R.id.date);
        date.setText(visit.getDate());
        TextView synced = (TextView) rowVisit.findViewById(R.id.synced);
        String str = context.getString(visit.isSynced() ? R.string.synced : R.string.in_cache);
        synced.setText(str);
        synced.setTextColor(visit.isSynced() ? Color.GREEN : Color.RED);

        return rowVisit;
    }
}
