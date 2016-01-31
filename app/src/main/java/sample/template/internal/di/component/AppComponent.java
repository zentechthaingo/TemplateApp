package sample.template.internal.di.component;

import dagger.Component;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.module.ActivityModule;
import sample.template.internal.di.module.AppModule;
import sample.template.internal.di.module.NetworkModule;
import sample.template.presentation.view.activity.BaseActivity;

/**
 * @author Tom Koptel
 */
@PerApplication
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
        }
)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    StubActivityComponent plus(ActivityModule activityModule);
}
