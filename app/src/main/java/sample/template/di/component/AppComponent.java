package sample.template.di.component;

import dagger.Component;
import sample.template.di.PerApplication;
import sample.template.di.module.AppModule;
import sample.template.di.module.NetworkModule;

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
    StubComponent createStubComponent();
}
