package sample.template.domain.route;

/**
 * @author Tom Koptel
 */
public interface RouteCaller<T> {
    rx.Observable<T> getOlderWithRetry();
    rx.Observable<T> getFirst();
}
