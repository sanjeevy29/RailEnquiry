package sanjeev.railenquiry.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import sanjeev.railenquiry.Activity.CancelledTrainActivity;
import sanjeev.railenquiry.Activity.FareMainActivity;
import sanjeev.railenquiry.Activity.LiveTrainMainActivity;
import sanjeev.railenquiry.Activity.PNRMainActivity;
import sanjeev.railenquiry.Activity.RescheduledTrainsActivity;
import sanjeev.railenquiry.Activity.SeatAvailabilityMainActivity;
import sanjeev.railenquiry.Activity.StationStatusMainActivity;
import sanjeev.railenquiry.Activity.TrainBetweenStationMainActivity;
import sanjeev.railenquiry.Activity.TrainScheduleActivity;
import sanjeev.railenquiry.MainActivity;


public class CommonClass {
    Activity activity;
    public SharedPreferences settings;

    public CommonClass(Activity activity) {
        this.activity = activity;
        //  settings = activity.getSharedPreferences(ConstValue.PREF_NAME, 0);
    }


    public void setSession(String key, String value) {
        settings.edit().putString(key, value).commit();
    }

    public String getSession(String key) {
        return settings.getString(key, "");
    }

    public void open_screen(int position) {
        Intent intent = null;
        switch (position) {
          case 0:
                intent = new Intent(activity, TrainBetweenStationMainActivity.class);
                break;
            case 1:
                intent = new Intent(activity, TrainScheduleActivity.class);
                break;
            case 2:
                intent = new Intent(activity, PNRMainActivity.class);
                break;
        /*    case 3:
                intent = new Intent(activity, ResultActivity.class);
                break;*/
            case 4:
                intent = new Intent(activity, SeatAvailabilityMainActivity.class);
                break;
        /*    case 5:
                intent = new Intent(activity, MainActivity.class);
                break;*/
            case 6:
                intent = new Intent(activity, StationStatusMainActivity.class);
                break;
          /*  case 7:
                intent = new Intent(activity, NewsActivity.class);
                break;*/
            case 8:
                intent = new Intent(activity, FareMainActivity.class);
                break;
            case 9:
                intent = new Intent(activity, CancelledTrainActivity.class);
                break;
            case 10:
                intent = new Intent(activity, RescheduledTrainsActivity.class);
                break;

            case 11:
                intent = new Intent(activity, LiveTrainMainActivity.class);
                break;
        }
        if (intent != null) {
            activity.startActivity(intent);
        }
    }
}
