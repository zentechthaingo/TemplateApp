package sample.template.domain.route.page;

import rx.Observable;
import sample.template.domain.route.Router;

/**
 * @author Tom Koptel
 */
public class ItemsLoader<T> {
    private final Router<T> mRouter;

    public ItemsLoader(Router<T> router) {
        mRouter = router;
    }

    public Observable<T> firstPage() {
        return (Observable<T>) new FirstPage<>(mRouter.getAllRoutes()).asObservable();
    }

    public Observable<T> olderPages() {
        return (Observable<T>) new OlderPage<>(mRouter.getAllRoutes()).asObservable();
    }
}
