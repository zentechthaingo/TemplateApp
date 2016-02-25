package sample.template;

import sample.template.di.component.AppComponent;
import sample.template.di.component.DaggerTestAppComponent;
import sample.template.di.TestAppModule;

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
