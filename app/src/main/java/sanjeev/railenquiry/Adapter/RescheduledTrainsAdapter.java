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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cancelled_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.train_number.setText(rescheduled.get(i).getTrain_number());
        viewHolder.train_name.setText(rescheduled.get(i).getTrain_name());
        viewHolder.train_source.setText(rescheduled.get(i).getTrain_source());

        viewHolder.train_dest.setText(rescheduled.get(i).getTrain_dest());


    }

    @Override
    public int getItemCount() {
        return rescheduled.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_number;
        private TextView train_name;
        private TextView train_source;

        private TextView train_dest;


        public ViewHolder(View view) {
            super(view);


            train_number = (TextView) view.findViewById(R.id.train_number);
            train_name = (TextView) view.findViewById(R.id.train_name);
            train_source = (TextView) view.findViewById(R.id.train_source);

            train_dest = (TextView) view.findViewById(R.id.train_dest);


        }
    }

}