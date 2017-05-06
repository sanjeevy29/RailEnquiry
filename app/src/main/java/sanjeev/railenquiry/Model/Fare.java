package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 4/28/2017.
 */

public class Fare {

    private String fare_code;
    private String fare_name;
    private String fare_price;

    private String train_no;
    private String train_name;
    private String quota_code;
    private String quota_name;
    private String from;
    private String to;

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

    public String getFare_code() {
        return fare_code;
    }

    public void setFare_code(String fare_code) {
        this.fare_code = fare_code;
    }

    public String getFare_name() {
        return fare_name;
    }

    public void setFare_name(String fare_name) {
        this.fare_name = fare_name;
    }

    public String getFare_price() {
        return fare_price;
    }

    public void setFare_price(String fare_price) {
        this.fare_price = fare_price;
    }
}
