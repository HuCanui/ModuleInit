package annotation.hucanhui.com.libapi;

import android.app.Application;
import android.util.Log;

/**
 * Created by hucanhui on 2018/6/5.
 */

public abstract class  ModuleApplication {
    /**
     * init your module configure
     * @param application
     */
    public ModuleApplication(Application application) {
        Log.e("hucanhui", "do your something!!!!!!!!!");
    }
}
