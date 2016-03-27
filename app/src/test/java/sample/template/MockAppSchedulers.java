package sample.template;

import org.mockito.Mock;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import sample.template.domain.AppSchedulers;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Tom Koptel
 */
public class MockAppSchedulers implements AppSchedulers {
    @Mock
    AppSchedulers mAppSchedulers;

    public MockAppSchedulers() {
        initMocks(this);
        when(mAppSchedulers.backgroundThread())
                .thenReturn(Schedulers.immediate());
        when(mAppSchedulers.uiThread())
                .thenReturn(Schedulers.immediate());
    }

    @Override
    public Scheduler uiThread() {
        return mAppSchedulers.uiThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return mAppSchedulers.backgroundThread();
    }
}
