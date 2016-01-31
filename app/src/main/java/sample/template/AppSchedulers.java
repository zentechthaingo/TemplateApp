package sample.template;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Tom Koptel
 */
public interface AppSchedulers {
    AppSchedulers DEFAULT = new AppSchedulers() {
        @Override
        public Scheduler uiThread() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler backgroundThread() {
            return Schedulers.io();
        }
    };

    rx.Scheduler uiThread();
    rx.Scheduler backgroundThread();
}
