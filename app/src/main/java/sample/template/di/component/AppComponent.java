package sample.template.di.component;

import dagger.Component;
import sample.template.di.PerApplication;
import sample.template.di.module.ActivityModule;
import sample.template.di.module.AppModule;
import sample.template.di.module.NetworkModule;
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

    StubScreenComponent createStubScreenComponent();
}
