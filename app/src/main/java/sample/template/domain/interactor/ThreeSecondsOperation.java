package sample.template.domain.interactor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func0;
import sample.template.AppSchedulers;
import sample.template.internal.di.PerApplication;

/**
 * @author Tom Koptel
 */
@PerApplication
public class ThreeSecondsOperation extends AbstractUseCase<Void, Void> {
    @Inject
    public ThreeSecondsOperation(AppSchedulers schedulers) {
        super(schedulers);
    }

    @Override
    public Observable<Void> buildUseCaseObservable(Void aVoid) {
        return rx.Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                } catch (InterruptedException e) {
                    return Observable.error(e);
                }
                return Observable.just(null);
            }
        });
    }
}
