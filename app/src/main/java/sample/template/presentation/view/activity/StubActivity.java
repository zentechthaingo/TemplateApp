package sample.template.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.template.GraphObject;
import sample.template.R;
import sample.template.di.component.StubActivityComponent;
import sample.template.di.component.StubScreenComponent;
import sample.template.di.module.ActivityModule;
import sample.template.lib.ComponentControllerActivity;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.entity.ItemViewModel;
import sample.template.presentation.presenter.StubPresenter;

public class StubActivity extends ComponentControllerActivity<StubScreenComponent> implements StubContract.View {

    @Bind(R.id.listView)
    ListView listView;

    @Inject
    StubPresenter presenter;

    private ArrayAdapter<ItemViewModel> adapter;

    private StubActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        activityComponent = getComponent().plus(new ActivityModule(this));
        activityComponent.inject(this);

        presenter.takeView(this);

        if (savedInstanceState == null) {
            presenter.loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void showResult(@NonNull List<ItemViewModel> result) {
        adapter.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected StubScreenComponent onCreateNonConfigurationComponent() {
        GraphObject graphObject = GraphObject.Factory.from(this);
        return graphObject
                .getComponent()
                .createStubScreenComponent();
    }
}
