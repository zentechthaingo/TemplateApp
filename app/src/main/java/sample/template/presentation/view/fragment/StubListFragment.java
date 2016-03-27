package sample.template.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.template.GraphObject;
import sample.template.R;
import sample.template.di.component.StubComponent;
import sample.template.presentation.component.presenter.PresenterControllerFragment;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.model.ItemViewModel;
import sample.template.presentation.presenter.StubPresenter;

/**
 * @author Tom Koptel
 */
public class StubListFragment extends PresenterControllerFragment<StubComponent, StubPresenter>
        implements StubContract.View {

    private ArrayAdapter<ItemViewModel> mAdapter;

    @Bind(R.id.listView)
    ListView listView;

    public static StubListFragment newInstance() {
        return new StubListFragment();
    }

    @Override
    protected StubComponent onCreateNonConfigurationComponent() {
        return GraphObject.Factory.from(getContext())
                .getComponent()
                .createStubComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.stub_list, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StubPresenter presenter = getComponent().getPresenter();
        presenter.bindView(this);
        presenter.loadData();
    }

    @Override
    public void showResult(@NonNull List<ItemViewModel> result) {
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
