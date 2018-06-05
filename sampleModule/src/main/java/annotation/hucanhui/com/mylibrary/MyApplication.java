package annotation.hucanhui.com.mylibrary;

import android.app.Application;
import android.util.Log;

import annotation.hucanhui.com.libannotation.ApplicationModule;
import annotation.hucanhui.com.libapi.ModuleApplication;

/**
 * Created by hucanhui on 2018/6/5.
 */


@ApplicationModule
public class MyApplication extends ModuleApplication{
    public MyApplication(Application application) {
        super(application);
    }
}
