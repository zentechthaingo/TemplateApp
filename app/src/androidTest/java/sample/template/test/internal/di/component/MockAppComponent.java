package sample.template.test.internal.di.component;

import dagger.Component;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.component.AppComponent;
import sample.template.internal.di.module.NetworkModule;
import sample.template.test.internal.di.model.MockAppModule;

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
