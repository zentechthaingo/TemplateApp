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
        FirstPage<T> page = new FirstPage<>(mRouter.getAllRoutes());
        Observable<T> observable = (Observable<T>) page.asObservable();
        return observable;
    }

    public Observable<T> olderPages() {
        OlderPage<T> page = new OlderPage<>(mRouter.getAllRoutes());
        Observable<T> observable = (Observable<T>) page.asObservable();
        return observable;
    }
}
