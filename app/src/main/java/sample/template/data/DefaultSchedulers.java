package sample.template.data;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.template.domain.AppSchedulers;

/**
 * @author Tom Koptel
 */
public final class DefaultSchedulers extends AppSchedulers {

    @Inject
    public DefaultSchedulers() {
    }

    @Override
    public Scheduler uiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }
}
