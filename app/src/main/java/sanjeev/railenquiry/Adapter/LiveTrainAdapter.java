package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sanjeev.railenquiry.Model.CancelledTrains;
import sanjeev.railenquiry.Model.LiveTrain;
import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;


public class LiveTrainAdapter extends RecyclerView.Adapter<LiveTrainAdapter.ViewHolder> {
    private List<LiveTrain> live;
    private Context context;

    public LiveTrainAdapter(Context context, List<LiveTrain> live) {
        this.live = live;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.live_train_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.station_code.setText(live.get(i).getRstation_code());
        viewHolder.actual_arrival.setText(live.get(i).getRact_arrival());
        viewHolder.actual_dept.setText(live.get(i).getRact_dept());
        viewHolder.status.setText(live.get(i).getRstatus());
        viewHolder.sch_arrival.setText(live.get(i).getSch_arrival());
        viewHolder.sch_dept.setText(live.get(i).getSch_dept());
        viewHolder.station_name.setText(live.get(i).getRstation_name());


    }

    @Override
    public int getItemCount() {
        return live.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView station_code;
        private TextView actual_arrival;
        private TextView actual_dept;
        private TextView status;
        private TextView sch_arrival;
        private TextView sch_dept;
        private TextView station_name;


        public ViewHolder(View view) {
            super(view);

            station_name = (TextView) view.findViewById(R.id.station_name);
            station_code = (TextView) view.findViewById(R.id.station_code);
            actual_arrival = (TextView) view.findViewById(R.id.actual_arrival);
            actual_dept = (TextView) view.findViewById(R.id.actual_dept);
            status = (TextView) view.findViewById(R.id.status);
            sch_arrival = (TextView) view.findViewById(R.id.sch_arrival);
            sch_dept = (TextView) view.findViewById(R.id.sch_dept);


        }
    }

}