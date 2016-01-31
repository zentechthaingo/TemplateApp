package android.template.internal.di.component;

import android.template.internal.di.module.AppModule;

import dagger.Component;

/**
 * @author Tom Koptel
 */
@Component(
        modules = {
                AppModule.class,
        }
)
public interface AppComponent {
}
