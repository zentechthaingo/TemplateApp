package android.template.internal.di.component;

import android.template.internal.di.module.AppModule;
import android.template.internal.di.module.NetworkModule;
import android.template.presentation.view.activity.BaseActivity;

import dagger.Component;

/**
 * @author Tom Koptel
 */
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
        }
)
public interface AppComponent {
    void inject(BaseActivity baseActivity);
}
