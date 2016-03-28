package sample.template.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.paginate.Paginate;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.template.R;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public class StubListWidget extends FrameLayout implements StubContract.View, Paginate.Callbacks {
    private ArrayAdapter<ItemViewModel> mAdapter;
    private boolean mLoadingInProgress;
    private boolean mHasLoadedAllItems;

    @Inject
    StubContract.Action mAction;

    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.loader)
    TextView loader;

    public StubListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void showFirstPage(@NonNull List<ItemViewModel> result) {
        mHasLoadedAllItems = result.isEmpty();

        mAdapter.clear();
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();

        Paginate.with(listView, this)
                .setLoadingTriggerThreshold(10)
                .addLoadingListItem(true)
                .build();

        listView.setVisibility(VISIBLE);
        mLoadingInProgress = false;
    }

    @Override
    public void showOlderPage(@NonNull List<ItemViewModel> result) {
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();

        listView.setVisibility(VISIBLE);
        mLoadingInProgress = false;
    }

    @Override
    public void showLoader() {
        loader.setVisibility(VISIBLE);
        loader.setText(getString(R.string.loading));
    }

    @Override
    public void hideLoader() {
        loader.setVisibility(INVISIBLE);
    }

    private String getString(@StringRes int id) {
        return getContext().getResources().getString(id);
    }

    @Override
    public void showError(String error) {
        listView.setVisibility(INVISIBLE);
        loader.setVisibility(VISIBLE);
        loader.setText(error);
    }

    @Override
    public void onLoadMore() {
        mAction.loadOlderPage();
        mLoadingInProgress = true;
    }

    @Override
    public boolean isLoading() {
        return mLoadingInProgress;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return mHasLoadedAllItems;
    }
}
