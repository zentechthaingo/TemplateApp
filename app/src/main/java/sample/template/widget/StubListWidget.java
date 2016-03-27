package sample.template.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public class StubListWidget extends ListView implements StubContract.View {
    private ArrayAdapter<ItemViewModel> mAdapter;

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
    public void showResult(@NonNull List<ItemViewModel> result) {
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();
    }
}
