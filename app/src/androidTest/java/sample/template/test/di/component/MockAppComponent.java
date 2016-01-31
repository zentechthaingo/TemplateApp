package sample.template.test.di.component;

import dagger.Component;
import sample.template.di.PerApplication;
import sample.template.di.component.AppComponent;
import sample.template.di.module.NetworkModule;
import sample.template.test.di.model.MockAppModule;

/**
 * @author Tom Koptel
 */
@PerApplication
@Component(
        modules = {
                MockAppModule.class,
                NetworkModule.class,
        }
)
public interface MockAppComponent extends AppComponent {
}
