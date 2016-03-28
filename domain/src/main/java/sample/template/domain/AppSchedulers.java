package sample.template.domain;

import rx.Observable;
import rx.Scheduler;

/**
 * @author Tom Koptel
 */
public abstract class  AppSchedulers {
    public  abstract Scheduler uiThread();
    public abstract rx.Scheduler backgroundThread();

    public final <T> Observable.Transformer<T, T> apply() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(backgroundThread())
                        .observeOn(uiThread());
            }
        };
    }
}
