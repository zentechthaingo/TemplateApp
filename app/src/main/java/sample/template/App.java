package sample.template;

import android.app.Application;

import sample.template.di.component.AppComponent;
import sample.template.di.component.DaggerAppComponent;
import sample.template.di.module.AppModule;

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
