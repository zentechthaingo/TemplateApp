package sample.template.domain.route;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.FuncN;

/**
 * @author Tom Koptel
 */
public class Router<T> {
    private final List<RouteCallerFactory<T>> mRouteFactories;

    public Router(List<RouteCallerFactory<T>> routeFactories) {
        mRouteFactories = routeFactories;
    }

    public Observable<List<RouteCaller<T>>> getAllRoutes() {
        List<Observable<List<RouteCaller<T>>>> callers = new ArrayList<>();

        for (RouteCallerFactory<T> routeFactory : mRouteFactories) {
            callers.add(routeFactory.getAllCallers());
        }

        return Observable.combineLatest(callers, new FuncN<List<RouteCaller<T>>>() {
            @Override
            public List<RouteCaller<T>> call(Object... calls) {
                List<RouteCaller<T>> callers = new ArrayList<>(calls.length);

                for (Object call : calls) {
                    callers.addAll((List<RouteCaller<T>>) call);
                }

                return callers;
            }
        });
    }
}
