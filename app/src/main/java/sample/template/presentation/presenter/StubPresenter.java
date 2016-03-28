package sample.template.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import sample.template.di.PerActivity;
import sample.template.presentation.component.presenter.BasePresenter;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
@PerActivity
public class StubPresenter extends BasePresenter<StubContract.View> implements StubContract.Action, StubContract.Model.Callback {
    private static final String TAG = "StubPresenter";

    private StubContract.Model mModel;

    @Inject
    public StubPresenter(
            StubContract.Model model
    ) {
        mModel = model;
    }

    @Override
    public void onBindView(StubContract.View view) {
        mModel.bind(this);
        loadFirstPage();
    }

    @Override
    public void loadFirstPage() {
        mView.showLoader();
        mModel.loadFirstPage();
    }

    @Override
    public void loadOlderPage() {
        mModel.loadOlderPage();
    }

    @Override
    public void onFirstPageLoaded(@NonNull List<ItemViewModel> items) {
        mView.hideLoader();
        mView.showFirstPage(items);
    }

    @Override
    public void onOlderPageLoaded(@NonNull List<ItemViewModel> items) {
        mView.showOlderPage(items);
    }

    @Override
    public void onFirstPageError(@NonNull Throwable throwable) {
        handleError("onFirstPageError", throwable);
    }

    @Override
    public void onOlderPageError(@NonNull Throwable throwable) {
        handleError("onOlderPageError", throwable);
    }

    private void handleError(@NonNull String tag, @NonNull Throwable throwable) {
        Log.e(TAG, tag, throwable);
        mView.hideLoader();
        mView.showError(throwable.getMessage());
    }

    @Override
    public void onDestroy() {
        mModel.unbind();
    }
}
