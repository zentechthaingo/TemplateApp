package sample.template.presentation.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import sample.template.AppSchedulers;
import sample.template.R;

public class StubActivity extends BaseActivity {

    @Bind(R.id.testView)
    TextView testView;

    @Inject
    AppSchedulers mSchedulers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);

        ButterKnife.bind(this);
        getAppComponent().inject(this);

        rx.Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                } catch (InterruptedException e) {
                    return Observable.error(e);
                }
                return Observable.just(null);
            }
        })
                .subscribeOn(mSchedulers.backgroundThread())
                .observeOn(mSchedulers.uiThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        testView.setText("After 3 seconds");
                    }
                });
    }
}
