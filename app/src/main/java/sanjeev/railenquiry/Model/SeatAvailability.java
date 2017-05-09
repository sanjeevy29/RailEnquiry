package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 5/2/2017.
 */

public class SeatAvailability {

    private String seat_date;
    private String seat_status;

    private String train_no;
    private String train_name;
    private String quota_code;
    private String quota_name;
    private String class_code;
    private String class_name;
    private String from;
    private String to;

    public String getSeat_date() {
        return seat_date;
    }

    public void setSeat_date(String seat_date) {
        this.seat_date = seat_date;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
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

    public String getQuota_code() {
        return quota_code;
    }

    public void setQuota_code(String quota_code) {
        this.quota_code = quota_code;
    }

    public String getQuota_name() {
        return quota_name;
    }

    public void setQuota_name(String quota_name) {
        this.quota_name = quota_name;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
