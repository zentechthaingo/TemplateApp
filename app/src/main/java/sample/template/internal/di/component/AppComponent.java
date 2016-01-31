package sample.template.internal.di.component;

import dagger.Component;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.module.AppModule;
import sample.template.internal.di.module.NetworkModule;
import sample.template.presentation.view.activity.BaseActivity;
import sample.template.presentation.view.activity.StubActivity;

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
    void inject(StubActivity activity);
}
