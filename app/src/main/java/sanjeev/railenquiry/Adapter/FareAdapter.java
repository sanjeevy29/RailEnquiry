package sanjeev.railenquiry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import sanjeev.railenquiry.Model.Fare;
import sanjeev.railenquiry.R;


public class FareAdapter extends RecyclerView.Adapter<FareAdapter.ViewHolder> {
    private List<Fare> fare;
    private Context context;

    public FareAdapter(Context context, List<Fare> fare) {
        this.fare = fare;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fare_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.train_fare_code.setText(fare.get(i).getFare_code());
        viewHolder.train_fare_name.setText(fare.get(i).getFare_name());
        viewHolder.train_fare.setText(fare.get(i).getFare_price());


    }

    @Override
    public int getItemCount() {
        return fare.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView train_fare_code;
        private TextView train_fare_name;
        private TextView train_fare;


        public ViewHolder(View view) {
            super(view);

            train_fare_code = (TextView) view.findViewById(R.id.train_fare_code);
            train_fare_name = (TextView) view.findViewById(R.id.train_fare_name);
            train_fare = (TextView) view.findViewById(R.id.train_fare);


        }
    }

}