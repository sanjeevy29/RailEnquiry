package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.TrainBetweenStation;
import sanjeev.railenquiry.R;


public class TrainBetweenStationAdapter extends RecyclerView.Adapter<TrainBetweenStationAdapter.ViewHolder> {
    private List<TrainBetweenStation> tbs;
    private Context context;

    public TrainBetweenStationAdapter(Context context, List<TrainBetweenStation> tbs) {
        this.tbs = tbs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.train_between_station_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.train_number.setText(tbs.get(i).getTrain_no());
        viewHolder.train_name.setText(tbs.get(i).getTrain_name());
        viewHolder.train_sch_dep.setText(tbs.get(i).getDeparture());


    }

    @Override
    public int getItemCount() {
        return tbs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_number;
        private TextView train_name;
        private TextView train_sch_dep;


        public ViewHolder(View view) {
            super(view);

            train_number = (TextView) view.findViewById(R.id.train_number);
            train_name = (TextView) view.findViewById(R.id.train_name);
            train_sch_dep = (TextView) view.findViewById(R.id.train_sch_dep);


        }
    }

}