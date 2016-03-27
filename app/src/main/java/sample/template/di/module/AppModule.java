package sample.template.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sample.template.App;
import sample.template.data.DefaultSchedulers;
import sample.template.domain.AppSchedulers;
import sample.template.di.ApplicationContext;
import sample.template.di.PerApplication;

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
    AppSchedulers providesAppSchedulers(DefaultSchedulers schedulers) {
        return schedulers;
    }
}
