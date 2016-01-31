package android.template;

import android.app.Application;
import android.template.internal.di.component.AppComponent;
import android.template.internal.di.component.DaggerAppComponent;
import android.template.internal.di.module.AppModule;

/**
 * @author Tom Koptel
 */
public class App extends Application implements GraphObject {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public AppComponent getComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return mAppComponent;
    }
}
