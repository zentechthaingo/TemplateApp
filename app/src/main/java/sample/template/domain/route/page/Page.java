package sample.template.domain.route.page;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.functions.Func1;
import sample.template.domain.route.RouteCaller;

/**
 * @author Tom Koptel
 */
public abstract class Page<T> {
    private final rx.Observable<List<RouteCaller<T>>> mCallers;

    private AtomicInteger mFailed = new AtomicInteger();
    private int backendCallsCount = 0;

    protected Page(Observable<List<RouteCaller<T>>> callers) {
        mCallers = callers;
    }

    public Observable<?> asObservable() {
        return mCallers.flatMap(new Func1<List<RouteCaller<T>>, Observable<?>>() {
            @Override
            public Observable<?> call(List<RouteCaller<T>> routeCallers) {
                backendCallsCount = routeCallers.size();
                List<Observable<?>> observables = new ArrayList<>();
                for (RouteCaller<T> routeCaller : routeCallers) {
                    Observable<T> observer = getRouteCall(routeCaller).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                        @Override
                        public Observable<? extends T> call(Throwable throwable) {
                            int fails = mFailed.incrementAndGet();
                            if (fails == backendCallsCount) {
                                return Observable.error(throwable);
                            }
                            return Observable.empty();
                        }
                    });
                    observables.add(observer);
                }
                return Observable.merge(observables);
            }
        });
    }

    protected abstract Observable<T> getRouteCall(RouteCaller<T> caller);
}
