package sample.template.test;

import sample.template.App;
import sample.template.internal.di.component.AppComponent;
import sample.template.test.internal.di.component.DaggerMockAppComponent;
import sample.template.test.internal.di.model.MockAppModule;

/**
 * @author Tom Koptel
 */
public class MockApp extends App {
    @Override
    public AppComponent getComponent() {
        return DaggerMockAppComponent.builder()
                .mockAppModule(new MockAppModule(this))
                .build();
    }
}
