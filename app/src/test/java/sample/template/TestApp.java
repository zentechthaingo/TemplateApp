package sample.template;

import sample.template.internal.di.component.AppComponent;
import sample.template.internal.di.component.DaggerTestAppComponent;
import sample.template.internal.di.model.TestAppModule;

/**
 * @author Tom Koptel
 */
public class TestApp extends App {
    @Override
    public AppComponent getComponent() {
        return DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }
}
