package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sanjeev.railenquiry.Model.CancelledTrains;
import sanjeev.railenquiry.Model.RescheduledTrains;
import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;


public class RescheduledTrainsAdapter extends RecyclerView.Adapter<RescheduledTrainsAdapter.ViewHolder> {
    private List<RescheduledTrains> rescheduled;
    private Context context;

    public RescheduledTrainsAdapter(Context context, List<RescheduledTrains> rescheduled) {
        this.rescheduled = rescheduled;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rescheduled_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.train_number.setText(rescheduled.get(i).getTrain_number());
        viewHolder.train_name.setText(rescheduled.get(i).getTrain_name());
        viewHolder.time_difference.setText(rescheduled.get(i).getTime_diff());

        viewHolder.rescheduled_time.setText(rescheduled.get(i).getRescheduled_time());
        viewHolder.rescheduled_date.setText(rescheduled.get(i).getRescheduled_date());
       /* viewHolder.to_code.setText(rescheduled.get(i).getTo_code());
        viewHolder.from_code.setText(rescheduled.get(i).getFrom_code());*/
    }

    @Override
    public int getItemCount() {
        return rescheduled.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_number;
        private TextView train_name;
        private TextView time_difference;
        private TextView rescheduled_time;
        private TextView rescheduled_date;
        private TextView to_code;
        private TextView from_code;


        public ViewHolder(View view) {
            super(view);


            train_number = (TextView) view.findViewById(R.id.train_number);
            train_name = (TextView) view.findViewById(R.id.train_name);
            time_difference = (TextView) view.findViewById(R.id.time_difference);
            rescheduled_time = (TextView) view.findViewById(R.id.rescheduled_time);
            rescheduled_date = (TextView) view.findViewById(R.id.rescheduled_date);


        }
    }

}