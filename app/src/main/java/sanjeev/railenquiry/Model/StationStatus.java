package sanjeev.railenquiry.Model;

/**
 * Created by sanjeev.yadav on 5/4/2017.
 */

public class StationStatus {

    private String train_no;
    private String train_name;
    private String dep;
    private String delayarrival;

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

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDelayarrival() {
        return delayarrival;
    }

    public void setDelayarrival(String delayarrival) {
        this.delayarrival = delayarrival;
    }
}
