package annotation.hucanhui.com.annotation;

import android.app.Application;
import android.util.Log;

import annotation.hucanhui.com.libapi.ModuleApplication;

/**
 * Created by hucanhui on 2018/6/5.
 */

public class MyApplication extends ModuleApplication {
    public MyApplication(Application application) {
        super(application);
        Log.e("hucanhui", "init success");
    }
}
