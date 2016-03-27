package sample.template.di;

import dagger.Module;
import dagger.Provides;
import sample.template.App;
import sample.template.domain.AppSchedulers;
import sample.template.MockAppSchedulers;
import sample.template.di.module.AppModule;

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
