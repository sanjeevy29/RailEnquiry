package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<Schedule> schedule;
    private Context context;

    public ScheduleAdapter(Context context, List<Schedule> auction) {
        this.schedule = auction;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.train_schedule_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {



        viewHolder.station_name.setText(schedule.get(i).getStation_name());
        viewHolder.arrival.setText(schedule.get(i).getArrival());
        viewHolder.departure.setText(schedule.get(i).getDeparture());

        viewHolder.distance.setText(schedule.get(i).getDistance());
        viewHolder.days.setText(schedule.get(i).getDays());
        viewHolder.halt.setText(schedule.get(i).getHalt());

        //  Picasso.with(context).load(android.get(i).getImg_venue()).into(viewHolder.img_venue);

    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_train_no;
        private TextView txt_train_name;
        private TextView train_runson;

        private TextView station_name;
        private TextView arrival;
        private TextView departure;

        private TextView distance;
        private TextView days;
        private TextView halt;


     //   private ImageView img_venue;


        public ViewHolder(View view) {
            super(view);



            station_name = (TextView)view.findViewById(R.id.station_name);
            arrival = (TextView)view.findViewById(R.id.arrival);
            departure = (TextView)view.findViewById(R.id.departure);

            distance = (TextView)view.findViewById(R.id.distance);
            days = (TextView)view.findViewById(R.id.days);
            halt = (TextView)view.findViewById(R.id.halt);


        }
    }

}