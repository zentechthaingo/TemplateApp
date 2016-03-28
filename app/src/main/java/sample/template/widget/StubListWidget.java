package sample.template.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
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
    private AtomicInteger lastPageIndex = new AtomicInteger(0);
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
    public void ready() {
        int index = lastPageIndex.get();
        mAction.loadFirstPage();

        for (int i = 1; i < index; i++) {
            mAction.loadOlderPage();
        }
    }

    @Override
    public void showFirstPage(@NonNull List<ItemViewModel> result) {
        mLoadingInProgress = false;
        mHasLoadedAllItems = result.isEmpty();

        mAdapter.clear();
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();

        Paginate.with(this, this)
                .setLoadingTriggerThreshold(30)
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

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.index = lastPageIndex.get();

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());
        lastPageIndex = new AtomicInteger(ss.index);
    }

    static class SavedState extends BaseSavedState {
        int index;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.index = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.index);
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
