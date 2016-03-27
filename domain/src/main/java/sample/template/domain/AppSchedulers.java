package sample.template.domain;

/**
 * @author Tom Koptel
 */
public interface AppSchedulers {
    rx.Scheduler uiThread();
    rx.Scheduler backgroundThread();
}
