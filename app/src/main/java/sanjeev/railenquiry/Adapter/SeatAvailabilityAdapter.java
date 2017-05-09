package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.Model.SeatAvailability;
import sanjeev.railenquiry.R;


public class SeatAvailabilityAdapter extends RecyclerView.Adapter<SeatAvailabilityAdapter.ViewHolder> {
    private List<SeatAvailability> seat;
    private Context context;

    public SeatAvailabilityAdapter(Context context, List<SeatAvailability> seat) {
        this.seat = seat;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.seat_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.date.setText(seat.get(i).getSeat_date());
        viewHolder.status.setText(seat.get(i).getSeat_status());



    }

    @Override
    public int getItemCount() {
        return seat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView status;



        public ViewHolder(View view) {
            super(view);

            date = (TextView) view.findViewById(R.id.date);
            status = (TextView) view.findViewById(R.id.status);



        }
    }

}