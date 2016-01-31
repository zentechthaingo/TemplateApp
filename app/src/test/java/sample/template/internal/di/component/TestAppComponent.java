package sample.template.internal.di.component;

import sample.template.internal.di.model.TestAppModule;

import dagger.Component;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.module.NetworkModule;

/**
 * @author Tom Koptel
 */
@PerApplication
@Component(
        modules = {
                TestAppModule.class,
                NetworkModule.class,
        }
)
public interface TestAppComponent extends AppComponent {
}
