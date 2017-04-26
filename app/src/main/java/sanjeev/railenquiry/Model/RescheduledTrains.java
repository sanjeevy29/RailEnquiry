package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 4/26/2017.
 */

public class RescheduledTrains {

    private String train_name;
    private String train_number;
    private String to_code;
    private String from_code;
    private String time_diff;
    private String rescheduled_date;
    private String rescheduled_time;

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    public String getTo_code() {
        return to_code;
    }

    public void setTo_code(String to_code) {
        this.to_code = to_code;
    }

    public String getFrom_code() {
        return from_code;
    }

    public void setFrom_code(String from_code) {
        this.from_code = from_code;
    }

    public String getTime_diff() {
        return time_diff;
    }

    public void setTime_diff(String time_diff) {
        this.time_diff = time_diff;
    }

    public String getRescheduled_date() {
        return rescheduled_date;
    }

    public void setRescheduled_date(String rescheduled_date) {
        this.rescheduled_date = rescheduled_date;
    }

    public String getRescheduled_time() {
        return rescheduled_time;
    }

    public void setRescheduled_time(String rescheduled_time) {
        this.rescheduled_time = rescheduled_time;
    }
}
