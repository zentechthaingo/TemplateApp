package sample.template.di.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;

import dagger.Module;
import dagger.Provides;
import sample.template.di.PerActivity;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@Module
public class LoaderModule extends ActivityModule {
    private final Fragment mFragment;

    public LoaderModule(FragmentActivity activity) {
        super(activity);
        mFragment = null;
    }

    public LoaderModule(Fragment fragment) {
        super(fragment.getActivity());
        mFragment = fragment;
    }

    @PerActivity
    @Provides
    public LoaderManager provideLoaderManager(FragmentActivity activity) {
        if (mFragment == null) {
            return activity.getSupportLoaderManager();
        }
        return mFragment.getLoaderManager();
    }
}
