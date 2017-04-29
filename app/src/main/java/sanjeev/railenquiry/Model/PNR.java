package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 4/29/2017.
 */

public class PNR {

    private String train_no;
    private String train_name;
    private String pnr;
    private String chart_prepared;
    private String current_status;
    private String booking_status;
    private String doj;
    private String ticketfrom;
    private String ticketto;
    private String boarding;
    private String destination;
    private String total_passenger;
    private String train_class;
    private String srno;

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getTrain_class() {
        return train_class;
    }

    public void setTrain_class(String train_class) {
        this.train_class = train_class;
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

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getChart_prepared() {
        return chart_prepared;
    }

    public void setChart_prepared(String chart_prepared) {
        this.chart_prepared = chart_prepared;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getTicketfrom() {
        return ticketfrom;
    }

    public void setTicketfrom(String ticketfrom) {
        this.ticketfrom = ticketfrom;
    }

    public String getTicketto() {
        return ticketto;
    }

    public void setTicketto(String ticketto) {
        this.ticketto = ticketto;
    }

    public String getBoarding() {
        return boarding;
    }

    public void setBoarding(String boarding) {
        this.boarding = boarding;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTotal_passenger() {
        return total_passenger;
    }

    public void setTotal_passenger(String total_passenger) {
        this.total_passenger = total_passenger;
    }
}
