package sample.template.data.route;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.AbstractRouteCaller;
import sample.template.domain.route.RouteCaller;
import sample.template.domain.route.RouteCallerFactory;

/**
 * @author Tom Koptel
 */
public final class FakeItemsSearchCallerFactory implements RouteCallerFactory<List<AppItem>> {
    private final RouteCaller<List<AppItem>> mCaller = new AbstractRouteCaller<List<AppItem>>(0, 40) {
        @Override
        protected Observable<List<AppItem>> performCall(int offset, int limit) {
            Log.d("app_search", String.format("offset %1$d limit %2$d", offset, limit));

            try {
                List<AppItem> appItems = makeCall(offset, limit);
                return Observable.just(appItems);
            } catch (Exception e) {
                return Observable.error(e);
            }
        }

        private List<AppItem> makeCall(int offset, int limit) throws Exception {
            List<AppItem> list = new ArrayList<>(limit);
            for (int i = offset + 1; i <= limit + offset; i++) {
                AppItem item = new AppItem("Unique Item: " + i);
                list.add(item);
            }
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            return list;
        }
    };
    private final List<RouteCaller<List<AppItem>>> mCallers = Collections.singletonList(mCaller);

    @Override
    public Observable<List<RouteCaller<List<AppItem>>>> getAllCallers() {
        return Observable.just(mCallers);
    }
}
