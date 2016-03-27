package sample.template.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import sample.template.GraphObject;
import sample.template.R;
import sample.template.di.component.StubComponent;
import sample.template.presentation.component.fragment.PresenterControllerFragment;
import sample.template.presentation.presenter.StubPresenter;
import sample.template.widget.StubListWidget;

/**
 * @author Tom Koptel
 */
public class StubListFragment extends PresenterControllerFragment<StubComponent, StubPresenter> {

    @Bind(R.id.listView)
    StubListWidget mWidget;

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
        StubListWidget root = (StubListWidget) inflater.inflate(R.layout.stub_list, container, false);
        return getComponent().inject(root);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StubPresenter presenter = getComponent().getPresenter();
        presenter.bindView(mWidget);
    }
}
