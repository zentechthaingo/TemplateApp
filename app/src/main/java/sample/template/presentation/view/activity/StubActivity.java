package sample.template.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.template.R;
import sample.template.internal.di.module.ActivityModule;
import sample.template.presentation.contract.StubContract;
import sample.template.presentation.presenter.StubPresenter;

public class StubActivity extends BaseActivity implements StubContract.View {

    @Bind(R.id.testView)
    TextView testView;

    @Inject
    StubPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);

        ButterKnife.bind(this);
        getAppComponent()
                .plus(new ActivityModule(this))
                .inject(this);

        mPresenter.injectView(this);
        mPresenter.loadData();
    }

    @Override
    public void showResult(@NonNull String result) {
        testView.setText(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
