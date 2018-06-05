package annotation.hucanhui.com.libapi;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hucanhui on 2018/6/5.
 */

public class ModuleHelper {
    private static List<String> classNames = new ArrayList<>();
    public static void initModule(Application application) {
        try {
            Log.e("hucanhui", "modules start init!!!!!!!!!!!!!!");
            Class moduleList = Class.forName("com.androidframwork.module.Application$$Module");
            Constructor moduleListConstructor = moduleList.getConstructor(List.class);
            moduleListConstructor.newInstance(classNames);
            if (classNames != null) {
                for (int i = 0; i < classNames.size(); i++) {
                    Class proxy = Class.forName(classNames.get(i));
                    Constructor constructor = proxy.getConstructor(Application.class);
                    constructor.newInstance(application);
                }
                Log.e("hucanhui", "all modules init success!!!!!!!!!!!!!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
