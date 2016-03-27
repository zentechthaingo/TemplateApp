package sample.template.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Subscribers;
import sample.template.domain.AppSchedulers;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.page.ItemsLoader;
import sample.template.di.PerActivity;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;
import sample.template.presentation.model.mapper.ItemViewMapper;

/**
 * @author Tom Koptel
 */
@PerActivity
public class StubPresenter extends Presenter<StubContract.View> implements StubContract.Action {
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
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void loadData() {
        mItemsLoader.firstPage()
                .map(new Func1<List<AppItem>, List<ItemViewModel>>() {
                    @Override
                    public List<ItemViewModel> call(List<AppItem> appItems) {
                        return mItemViewMapper.toViewModels(appItems);
                    }
                })
                .subscribeOn(mSchedulers.backgroundThread())
                .observeOn(mSchedulers.uiThread())
                .subscribe(
                        Subscribers.create(new Action1<List<ItemViewModel>>() {
                            @Override
                            public void call(List<ItemViewModel> items) {
                                getView().showResult(items);
                            }
                        })
                );
    }
}
