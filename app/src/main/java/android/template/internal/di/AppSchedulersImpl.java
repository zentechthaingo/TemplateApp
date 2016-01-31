package android.template.internal.di;

import android.template.AppSchedulers;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Tom Koptel
 */
@PerApplication
public class AppSchedulersImpl implements AppSchedulers {

    @Inject
    public AppSchedulersImpl() {
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
