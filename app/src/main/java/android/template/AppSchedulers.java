package android.template;

/**
 * @author Tom Koptel
 */
public interface AppSchedulers {
    rx.Scheduler uiThread();
    rx.Scheduler backgroundThread();
}
