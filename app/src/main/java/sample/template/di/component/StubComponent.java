package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerPage;
import sample.template.di.module.StubModule;
import sample.template.presentation.component.presenter.HasPresenter;
import sample.template.presentation.presenter.StubPresenter;
import sample.template.widget.StubListWidget;

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
    StubListWidget inject(StubListWidget page);
}
