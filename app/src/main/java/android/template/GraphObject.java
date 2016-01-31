package android.template;

import android.content.Context;
import android.template.internal.di.component.AppComponent;

/**
 * @author Tom Koptel
 */
public interface GraphObject {
    AppComponent getComponent();

    class Factory {
        private Factory() {}

        public static GraphObject get(Context context) {
            return ((App) context.getApplicationContext());
        }
    }
}
