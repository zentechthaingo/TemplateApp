package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerActivity;
import sample.template.di.module.ActivityModule;
import sample.template.di.module.StubActivityModule;
import sample.template.presentation.view.activity.StubActivity;

/**
 * @author Tom Koptel
 */
@PerActivity
@Subcomponent(
        modules = {
                ActivityModule.class,
                StubActivityModule.class,
        }
)
public interface StubActivityComponent {
    void inject(StubActivity activity);
}
