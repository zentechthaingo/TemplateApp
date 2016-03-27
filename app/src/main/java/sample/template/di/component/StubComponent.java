package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerPage;
import sample.template.di.module.StubModule;
import sample.template.presentation.component.presenter.HasPresenter;
import sample.template.presentation.presenter.StubPresenter;
import sample.template.presentation.view.fragment.StubListFragment;

/**
 * @author Tom Koptel
 */
@PerPage
@Subcomponent(
        modules = {
                StubModule.class,
        }
)
public interface StubComponent extends HasPresenter<StubPresenter> {
    void inject(StubListFragment page);
}
