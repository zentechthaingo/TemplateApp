package sample.template.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import sample.template.GraphObject;
import sample.template.R;
import sample.template.di.component.StubActivityComponent;
import sample.template.di.component.StubPageComponent;
import sample.template.di.module.LoaderModule;
import sample.template.presentation.component.fragment.PresenterControllerFragment;
import sample.template.presentation.presenter.StubPresenter;
import sample.template.widget.StubListWidget;

/**
 * @author Tom Koptel
 */
public class StubListFragment extends PresenterControllerFragment<StubPageComponent, StubPresenter> {

    @Bind(R.id.list)
    StubListWidget mWidget;

    private StubActivityComponent mActivityComponent;

    public static StubListFragment newInstance() {
        return new StubListFragment();
    }

    @Override
    protected StubPageComponent onCreateNonConfigurationComponent() {
        return GraphObject.Factory.from(getContext())
                .getComponent()
                .createStubComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StubListWidget root = (StubListWidget) inflater.inflate(R.layout.stub_list_widget, container, false);
        return activityComponent().inject(root);
    }

    private StubActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = getComponent().plus(new LoaderModule(this));
        }
        return mActivityComponent;
    }

    @Override
    public StubPresenter getPresenter() {
        return activityComponent().getPresenter();
    }
}
