package sample.template.internal.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import sample.template.internal.di.ActivityContext;
import sample.template.internal.di.PerActivity;

/**
 * @author Tom Koptel
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @ActivityContext
    @PerActivity
    @Provides
    Activity providesActivity() {
        return mActivity;
    }
}
