package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerScreen;
import sample.template.di.module.ActivityModule;
import sample.template.di.module.StubActivityModule;

/**
 * @author Tom Koptel
 */
@PerScreen
@Subcomponent(
        modules = {StubActivityModule.class}
)
public interface StubScreenComponent {
    StubActivityComponent plus(ActivityModule module);
}
