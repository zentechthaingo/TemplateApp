package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerActivity;
import sample.template.di.module.LoaderModule;
import sample.template.di.module.StubActivityModule;
import sample.template.presentation.component.presenter.HasPresenter;
import sample.template.presentation.presenter.StubPresenter;
import sample.template.widget.StubListWidget;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@PerActivity
@Subcomponent(
        modules = {
                LoaderModule.class,
                StubActivityModule.class
        }
)
public interface StubActivityComponent extends HasPresenter<StubPresenter> {
    StubListWidget inject(StubListWidget page);
}
