package sample.template.presentation.model;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import sample.template.di.PerPage;
import sample.template.domain.AppSchedulers;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.page.ItemsLoader;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.mapper.ItemViewMapper;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@PerPage
public class StubDataModel implements StubContract.Model {

    private ItemsLoader<List<AppItem>> mItemsLoader;
    private ItemViewMapper mViewMapper;
    private AppSchedulers mSchedulers;
    private Callback mCallback;

    private Observable<List<ItemViewModel>> mFirstPageLoaderTask;
    private Observable<List<ItemViewModel>> mOlderPageLoaderTask;
    private Subscription mFirstPageSubscription;
    private Subscription mOlderPageSubscription;

    @Inject
    public StubDataModel(ItemsLoader<List<AppItem>> itemsLoader, ItemViewMapper viewMapper, AppSchedulers schedulers) {
        mItemsLoader = itemsLoader;
        mViewMapper = viewMapper;
        mSchedulers = schedulers;
    }

    @Override
    public void bind(Callback callback) {
        mCallback = callback;
        if (mFirstPageLoaderTask != null) {
            subscribeToFirstPageLoaderTask(mFirstPageLoaderTask);
        }
        if (mOlderPageLoaderTask != null) {
            subscribeToOlderPageLoaderTask(mOlderPageLoaderTask);
        }
    }

    @Override
    public void unbind() {
        mCallback = null;
        unsubscribe(mFirstPageSubscription);
        unsubscribe(mOlderPageSubscription);
    }

    private void unsubscribe(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadFirstPage() {
        mFirstPageLoaderTask = toUiOperation(mItemsLoader.firstPage());
        subscribeToFirstPageLoaderTask(mFirstPageLoaderTask);
    }

    private void subscribeToFirstPageLoaderTask(Observable<List<ItemViewModel>> loaderTask) {
        mFirstPageSubscription = loaderTask.subscribe(
                new Action1<List<ItemViewModel>>() {
                    @Override
                    public void call(List<ItemViewModel> itemViewModels) {
                        mCallback.onFirstPageLoaded(itemViewModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mCallback.onFirstPageError(throwable);
                    }
                });
    }

    @Override
    public void loadOlderPage() {
        mOlderPageLoaderTask = toUiOperation(mItemsLoader.olderPages());
        subscribeToOlderPageLoaderTask(mOlderPageLoaderTask);
    }

    private void subscribeToOlderPageLoaderTask(Observable<List<ItemViewModel>> loaderTask) {
        mOlderPageSubscription = loaderTask.subscribe(
                new Action1<List<ItemViewModel>>() {
                    @Override
                    public void call(List<ItemViewModel> itemViewModels) {
                        mCallback.onOlderPageLoaded(itemViewModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mCallback.onOlderPageError(throwable);
                    }
                });
    }

    private Observable<List<ItemViewModel>> toUiOperation(Observable<List<AppItem>> operation) {
        return operation.map(new Func1<List<AppItem>, List<ItemViewModel>>() {
            @Override
            public List<ItemViewModel> call(List<AppItem> appItems) {
                return mViewMapper.toViewModels(appItems);
            }
        }).compose(mSchedulers.<List<ItemViewModel>>apply());
    }
}
