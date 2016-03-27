package sample.template.domain.interactor;

/**
 * @author Tom Koptel
 */


import org.jetbrains.annotations.NotNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import sample.template.domain.AppSchedulers;

public abstract class AbstractUseCase<Result, Argument> {
    private final AppSchedulers mSchedulers;
    private Subscription subscription = Subscriptions.empty();

    protected AbstractUseCase(@NotNull AppSchedulers schedulers) {
        mSchedulers = schedulers;
    }

    public abstract Observable<Result> buildUseCaseObservable(Argument argument);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable(Argument)}.
     */
    public void execute(@NotNull Argument argument, @NotNull Subscriber<? super Result> useCaseSubscriber) {
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
