package sample.template.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import sample.template.di.ActivityContext;
import sample.template.di.PerPage;

/**
 * @author Tom Koptel
 */
@Module
public class ActivityModule {
    private final FragmentActivity mActivity;

    public ActivityModule(FragmentActivity activity) {
        mActivity = activity;
    }

    @PerPage
    @Provides
    FragmentActivity providesActivity() {
        return mActivity;
    }

    @PerPage
    @ActivityContext
    @Provides
    Context providesActivitContext() {
        return mActivity;
    }
}
