package sample.template.lib;


import android.support.annotation.NonNull;

public interface ComponentFactory<C> {
    /**
     * Create a new instance of a Component
     *
     * @return The Component instance
     */
    @NonNull
    C createComponent();
}