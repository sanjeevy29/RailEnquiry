package sanjeev.railenquiry.Activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


/**
 * Created by sanjeev.yadav on 5/4/2017.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Class aClass;

    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
