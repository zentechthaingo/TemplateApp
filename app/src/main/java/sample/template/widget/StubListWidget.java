package sample.template.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.paginate.Paginate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public class StubListWidget extends ListView implements StubContract.View, Paginate.Callbacks {
    private ArrayAdapter<ItemViewModel> mAdapter;
    private final AtomicInteger lastPageIndex = new AtomicInteger(0);
    private boolean mLoadingInProgress;
    private boolean mHasLoadedAllItems;

    @Inject
    StubContract.Action mAction;

    public StubListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        setAdapter(mAdapter);
    }

    @Override
    public void showFirstPage(@NonNull List<ItemViewModel> result) {
        mLoadingInProgress = false;
        mHasLoadedAllItems = result.isEmpty();

        mAdapter.clear();
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();

        Paginate.with(this, this)
                .setLoadingTriggerThreshold(20)
                .addLoadingListItem(true)
                .build();
    }

    @Override
    public void showOlderPage(@NonNull List<ItemViewModel> result) {
        mLoadingInProgress = false;

        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void cancelPageLoader() {
        mLoadingInProgress = false;
    }

    @Override
    public void onLoadMore() {
        lastPageIndex.incrementAndGet();
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
