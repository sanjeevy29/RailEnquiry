package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import sanjeev.railenquiry.Model.StationStatus;
import sanjeev.railenquiry.R;


public class StationStatusAdapter extends RecyclerView.Adapter<StationStatusAdapter.ViewHolder> {
    private List<StationStatus> station;
    private Context context;

    public StationStatusAdapter(Context context, List<StationStatus> station) {
        this.station = station;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.station_status_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.train_name.setText(station.get(i).getTrain_name());
        viewHolder.train_no.setText(station.get(i).getTrain_no());
        viewHolder.train_sch_dep.setText(station.get(i).getDep());


    }

    @Override
    public int getItemCount() {
        return station.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_name;
        private TextView train_no;
        private TextView train_sch_dep;


        public ViewHolder(View view) {
            super(view);

            train_name = (TextView) view.findViewById(R.id.train_number);
            train_no = (TextView) view.findViewById(R.id.train_name);
            train_sch_dep = (TextView) view.findViewById(R.id.train_sch_dep);


        }
    }

}