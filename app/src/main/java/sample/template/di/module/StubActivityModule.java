package sample.template.di.module;

import dagger.Module;
import dagger.Provides;
import sample.template.di.PerActivity;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.StubDataModel;
import sample.template.presentation.presenter.StubPresenter;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@Module
public class StubActivityModule {

    @PerActivity
    @Provides
    StubPresenter providesPresenter(StubDataModel model) {
        return new StubPresenter(model);
    }

    @PerActivity
    @Provides
    StubContract.Action providesActionListener(StubPresenter presenter) {
        return presenter;
    }
}
