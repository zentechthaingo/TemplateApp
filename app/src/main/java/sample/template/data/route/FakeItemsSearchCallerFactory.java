package sample.template.data.route;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func0;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.AbstractRouteCaller;
import sample.template.domain.route.RouteCaller;
import sample.template.domain.route.RouteCallerFactory;

/**
 * @author Tom Koptel
 */
public final class FakeItemsSearchCallerFactory implements RouteCallerFactory<List<AppItem>> {
    private final Map<PageToken, Observable<List<AppItem>>> mCache = new HashMap<>();

    private final RouteCaller<List<AppItem>> mCaller = new AbstractRouteCaller<List<AppItem>>(0, 40) {
        @Override
        protected Observable<List<AppItem>> performCall(int offset, int limit) {
            Log.d("app_search", String.format("offset %1$d limit %2$d", offset, limit));

            PageToken token = new PageToken(offset, limit);
            Observable<List<AppItem>> memory = memory(token);
            Observable<List<AppItem>> network = network(token);
            return Observable.concat(memory, network).first();
        }

        private Observable<List<AppItem>> memory(PageToken token) {
            Observable<List<AppItem>> memory = mCache.get(token);
            if (memory == null) {
                return Observable.empty();
            }
            return memory;
        }

        private Observable<List<AppItem>> network(final PageToken token) {
            Observable<List<AppItem>> cache = Observable.defer(new Func0<Observable<List<AppItem>>>() {
                @Override
                public Observable<List<AppItem>> call() {
                    try {
                        return Observable.just(makeCall(token.limit, token.offset));
                    } catch (Exception e) {
                        return Observable.error(e);
                    }
                }
            }).cache();
            mCache.put(token, cache);
            return cache;
        }

        private List<AppItem> makeCall(int limit, int offset) throws Exception {
            List<AppItem> list = new ArrayList<>(limit);
            for (int i = offset; i < limit + offset; i++) {
                list.add(new AppItem("Unique Item: " + i));
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

    private static class PageToken {
        private final int offset;
        private final int limit;

        private PageToken(int offset, int limit) {
            this.offset = offset;
            this.limit = limit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PageToken token = (PageToken) o;

            if (offset != token.offset) return false;
            return limit == token.limit;
        }

        @Override
        public int hashCode() {
            int result = offset;
            result = 31 * result + limit;
            return result;
        }
    }
}
