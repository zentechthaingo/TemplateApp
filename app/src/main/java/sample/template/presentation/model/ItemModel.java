package sample.template.presentation.model;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import sample.template.AppSchedulers;
import sample.template.di.PerScreen;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.page.ItemsLoader;
import sample.template.presentation.entity.ItemViewModel;
import sample.template.presentation.entity.mapper.ItemViewMapper;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@PerScreen
public class ItemModel {
    private ItemsLoader<List<AppItem>> itemsLoader;
    private ItemViewMapper itemViewMapper;
    private AppSchedulers schedulers;
    private Callback callback;
    private Subscription subscription;

    @Inject
    public ItemModel(
            ItemsLoader<List<AppItem>> itemsLoader,
            ItemViewMapper itemViewMapper,
            AppSchedulers schedulers
    ) {
        this.itemsLoader = itemsLoader;
        this.itemViewMapper = itemViewMapper;
        this.schedulers = schedulers;
    }

    public void subscibe(Callback callback) {
        this.callback = callback;
    }

    public void unsubscribe() {
        subscription.unsubscribe();
        callback = null;
    }

    public void loadData() {
        subscription = itemsLoader.firstPage()
                .map(new Func1<List<AppItem>, List<ItemViewModel>>() {
                    @Override
                    public List<ItemViewModel> call(List<AppItem> appItems) {
                        return itemViewMapper.toViewModels(appItems);
                    }
                })
                .subscribeOn(schedulers.backgroundThread())
                .observeOn(schedulers.uiThread())
                .subscribe(
                        new Action1<List<ItemViewModel>>() {
                            @Override
                            public void call(final List<ItemViewModel> itemViewModels) {
                                callback.onDataResult(new Result<List<ItemViewModel>>() {
                                    @Override
                                    public boolean isSuccess() {
                                        return true;
                                    }

                                    @Override
                                    public List<ItemViewModel> getData() {
                                        return itemViewModels;
                                    }
                                });
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                callback.onDataResult(new Result<List<ItemViewModel>>() {
                                    @Override
                                    public boolean isSuccess() {
                                        return false;
                                    }

                                    @Override
                                    public List<ItemViewModel> getData() {
                                        return null;
                                    }
                                });
                            }
                        }
                );
    }


    public interface Callback {
        void onDataResult(Result<List<ItemViewModel>> result);
    }
}
