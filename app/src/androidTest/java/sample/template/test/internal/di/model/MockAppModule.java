package sample.template.test.internal.di.model;

import android.os.AsyncTask;

import org.mockito.Mock;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.template.App;
import sample.template.AppSchedulers;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.module.AppModule;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Tom Koptel
 */
@Module
public class MockAppModule extends AppModule {

    @Mock
    AppSchedulers mAppSchedulers;

    public MockAppModule(App app) {
        super(app);
        initMocks(this);
        when(mAppSchedulers.backgroundThread())
                .thenReturn(Schedulers.from(AsyncTask.SERIAL_EXECUTOR));
        when(mAppSchedulers.uiThread())
                .thenReturn(AndroidSchedulers.mainThread());
    }

    @Provides
    @PerApplication
    AppSchedulers providesAppSchedulers() {
        return mAppSchedulers;
    }
}
