package annotation.hucanhui.com.annotation;

import android.app.Application;

import annotation.hucanhui.com.libapi.ModuleHelper;


/**
 * Created by hucanhui on 2018/6/5.
 */


public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ModuleHelper.initModule(this);
    }
}
