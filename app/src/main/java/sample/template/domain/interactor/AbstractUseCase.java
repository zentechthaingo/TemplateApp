package sample.template.domain.interactor;

/**
 * @author Tom Koptel
 */

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import sample.template.AppSchedulers;

public abstract class AbstractUseCase<Result, Argument> {
    private final AppSchedulers mSchedulers;
    private Subscription subscription = Subscriptions.empty();

    protected AbstractUseCase(AppSchedulers schedulers) {
        mSchedulers = schedulers;
    }

    protected abstract Observable<Result> buildUseCaseObservable(Argument argument);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable(Argument)}.
     */
    public void execute(@NonNull Argument argument, @NonNull Subscriber<? super Result> useCaseSubscriber) {
        Observable<Result> command = this.buildUseCaseObservable(argument);
        this.subscription = command
                .subscribeOn(mSchedulers.backgroundThread())
                .observeOn(mSchedulers.uiThread())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
