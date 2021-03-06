package sample.template.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sample.template.di.ActivityContext;
import sample.template.di.PerActivity;

/**
 * @author Tom Koptel
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity providesActivity() {
        return mActivity;
    }

    @PerActivity
    @ActivityContext
    @Provides
    Context providesActivitContext() {
        return mActivity;
    }
}
