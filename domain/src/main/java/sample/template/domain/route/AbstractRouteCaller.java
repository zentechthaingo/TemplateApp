package sample.template.domain.route;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author Tom Koptel
 */
public abstract class AbstractRouteCaller<T> implements RouteCaller<T> {
    private final int mStartPage;
    private final int mItemsPerPage;

    private final AtomicInteger olderPageOffset;
    private final Queue<Integer> olderFailedButRetryLater = new LinkedBlockingQueue<>();

    public AbstractRouteCaller(int startPage, int itemsPerPage) {
        mStartPage = startPage;
        mItemsPerPage = itemsPerPage;
        olderPageOffset = new AtomicInteger(mStartPage);
    }

    @Override
    public rx.Observable<T> getOlderWithRetry() {
        final int pageOffset;
        if (olderFailedButRetryLater.isEmpty()) {
            pageOffset = olderPageOffset.addAndGet(mItemsPerPage);
        } else {
            pageOffset = olderFailedButRetryLater.poll();
        }
        return performCall(pageOffset, mItemsPerPage)
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        olderFailedButRetryLater.add(pageOffset);
                    }
                });
    }

    @Override
    public rx.Observable<T> getFirst() {
        return performCall(mStartPage, mItemsPerPage);
    }

    protected abstract Observable<T> performCall(int offset, int limit);
}
