package android.template.internal.di.module;

import android.content.Context;
import android.template.App;
import android.template.AppSchedulers;
import android.template.internal.di.AppSchedulersImpl;
import android.template.internal.di.ApplicationContext;
import android.template.internal.di.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tom Koptel
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @ApplicationContext
    @PerApplication
    @Provides
    Context providesAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    AppSchedulers providesAppSchedulers(AppSchedulersImpl schedulers) {
        return schedulers;
    }
}
