package sample.template.internal.di.model;

import dagger.Module;
import dagger.Provides;
import sample.template.App;
import sample.template.AppSchedulers;
import sample.template.MockAppSchedulers;
import sample.template.internal.di.PerApplication;
import sample.template.internal.di.module.AppModule;

/**
 * @author Tom Koptel
 */
@Module
public class TestAppModule extends AppModule {
    public TestAppModule(App app) {
        super(app);
    }

    @Provides
    @PerApplication
    AppSchedulers providesAppSchedulers() {
        return new MockAppSchedulers();
    }
}
