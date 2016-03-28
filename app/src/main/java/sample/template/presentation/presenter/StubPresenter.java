package sample.template.presentation.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import sample.template.di.PerPage;
import sample.template.domain.AppSchedulers;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.page.ItemsLoader;
import sample.template.presentation.component.presenter.BasePresenter;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;
import sample.template.presentation.model.mapper.ItemViewMapper;

/**
 * @author Tom Koptel
 */
@PerPage
public class StubPresenter extends BasePresenter<StubContract.View> implements StubContract.Action {
    private ItemsLoader<List<AppItem>> mItemsLoader;
    private ItemViewMapper mItemViewMapper;
    private AppSchedulers mSchedulers;

    @Inject
    public StubPresenter(
            ItemsLoader<List<AppItem>> itemsLoader,
            ItemViewMapper itemViewMapper,
            AppSchedulers schedulers
    ) {
        mItemsLoader = itemsLoader;
        mItemViewMapper = itemViewMapper;
        mSchedulers = schedulers;
    }

    @Override
    public void onBindView(StubContract.View view) {
        view.ready();
    }

    @Override
    public void loadFirstPage() {
        loadPage(mItemsLoader.firstPage(), new Action1<List<ItemViewModel>>() {
            @Override
            public void call(List<ItemViewModel> items) {
                view.showFirstPage(items);
            }
        });
    }

    @Override
    public void loadOlderPage() {
        loadPage(mItemsLoader.olderPages(), new Action1<List<ItemViewModel>>() {
            @Override
            public void call(List<ItemViewModel> items) {
                view.showOlderPage(items);
            }
        });
    }

    private void loadPage(Observable<List<AppItem>> items, Action1<List<ItemViewModel>> success) {
        items
                .map(new Func1<List<AppItem>, List<ItemViewModel>>() {
                    @Override
                    public List<ItemViewModel> call(List<AppItem> appItems) {
                        return mItemViewMapper.toViewModels(appItems);
                    }
                })
                .subscribeOn(mSchedulers.backgroundThread())
                .observeOn(mSchedulers.uiThread())
                .subscribe(
                        success,
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.cancelPageLoader();
                                Log.e("StubPresenter", "Crashed", throwable);
                            }
                        }
                );
    }
}
