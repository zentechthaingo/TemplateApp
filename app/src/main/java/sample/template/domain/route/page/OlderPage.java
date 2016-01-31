package sample.template.domain.route.page;

import java.util.List;

import rx.Observable;
import sample.template.domain.route.RouteCaller;

/**
 * @author Tom Koptel
 */
public class OlderPage<T> extends Page<T> {
    protected OlderPage(Observable<List<RouteCaller<T>>> callers) {
        super(callers);
    }

    @Override
    protected Observable<T> getRouteCall(RouteCaller<T> caller) {
        return caller.getOlderWithRetry();
    }
}