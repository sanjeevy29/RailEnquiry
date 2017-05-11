package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 5/10/2017.
 */

public class TrainBetweenStation {

    private String train_from;
    private String train_to;
    private String train_no;
    private String train_name;
    private String arrival;
    private String departure;
    private String travel_distance;


    public String getTrain_from() {
        return train_from;
    }

    public void setTrain_from(String train_from) {
        this.train_from = train_from;
    }

    public String getTrain_to() {
        return train_to;
    }

    public void setTrain_to(String train_to) {
        this.train_to = train_to;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getTravel_distance() {
        return travel_distance;
    }

    public void setTravel_distance(String travel_distance) {
        this.travel_distance = travel_distance;
    }
}
