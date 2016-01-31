package sample.template.internal.di.component;

import dagger.Subcomponent;
import sample.template.internal.di.PerActivity;
import sample.template.internal.di.module.ActivityModule;
import sample.template.presentation.view.activity.StubActivity;

/**
 * @author Tom Koptel
 */
@PerActivity
@Subcomponent(
        modules = {
                ActivityModule.class
        }
)
public interface StubActivityComponent {
    void inject(StubActivity activity);
}
