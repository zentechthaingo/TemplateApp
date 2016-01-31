package sample.template.presentation.presenter;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.observers.Subscribers;
import sample.template.domain.interactor.ThreeSecondsOperation;
import sample.template.internal.di.PerActivity;
import sample.template.presentation.contract.StubContract;

/**
 * @author Tom Koptel
 */
@PerActivity
public class StubPresenter extends Presenter<StubContract.View> implements StubContract.Action {
    private final ThreeSecondsOperation mThreeSecondsOperation;

    @Inject
    public StubPresenter(ThreeSecondsOperation threeSecondsOperation) {
        mThreeSecondsOperation = threeSecondsOperation;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        mThreeSecondsOperation.unsubscribe();
    }

    @Override
    public void loadData() {
        mThreeSecondsOperation.execute(null,
                Subscribers.create(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getView().showResult("After 3 seconds");
                    }
                }));
    }
}
