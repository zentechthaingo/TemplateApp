package android.template.internal.di.module;

import android.app.Activity;
import android.template.internal.di.ActivityContext;
import android.template.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

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
