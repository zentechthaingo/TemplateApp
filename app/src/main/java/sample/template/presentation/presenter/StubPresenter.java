package sample.template.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import sample.template.di.PerActivity;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.entity.ItemViewModel;
import sample.template.presentation.model.ItemModel;
import sample.template.presentation.model.Result;

/**
 * @author Tom Koptel
 */
@PerActivity
public class StubPresenter extends Presenter<StubContract.View> implements StubContract.Action, ItemModel.Callback {
    private ItemModel itemModel;

    @Inject
    public StubPresenter(
            ItemModel itemModel
    ) {
        super(StubContract.View.class);
        this.itemModel = itemModel;
    }

    @Override
    public void onBindView(StubContract.View view) {
        itemModel.subscibe(this);
    }

    @Override
    public void onUnbindView() {
        itemModel.unsubscribe();
    }

    @Override
    public void loadData() {
        itemModel.loadData();
    }

    @Override
    public void onDataResult(Result<List<ItemViewModel>> result) {
        if (result.isSuccess()) {
            getView().showResult(result.getData());
        }
    }
}
