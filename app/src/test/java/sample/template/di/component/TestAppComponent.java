package sample.template.di.component;

import sample.template.di.TestAppModule;

import dagger.Component;
import sample.template.di.PerApplication;
import sample.template.di.module.NetworkModule;

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
