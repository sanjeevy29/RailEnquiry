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
import sanjeev.railenquiry.Model.PNR;
import sanjeev.railenquiry.Model.Schedule;
import sanjeev.railenquiry.R;


public class PNRAdapter extends RecyclerView.Adapter<PNRAdapter.ViewHolder> {
    private List<PNR> pnr;
    private Context context;

    public PNRAdapter(Context context, List<PNR> pnr) {
        this.pnr = pnr;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pnr_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.srno.setText(pnr.get(i).getSrno());
        viewHolder.booking_status.setText(pnr.get(i).getBooking_status());
        viewHolder.current_status.setText(pnr.get(i).getCurrent_status());


    }

    @Override
    public int getItemCount() {
        return pnr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView srno;
        private TextView booking_status;
        private TextView current_status;


        public ViewHolder(View view) {
            super(view);

            srno = (TextView) view.findViewById(R.id.srno);
            booking_status = (TextView) view.findViewById(R.id.booking_status);
            current_status = (TextView) view.findViewById(R.id.current_status);


        }
    }

}