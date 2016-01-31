package sample.template.presentation.presenter;

import android.support.annotation.NonNull;

/**
 * @author Tom Koptel
 */
public abstract class Presenter<View> {
    @NonNull
    private View mView;

    public void injectView(@NonNull View view) {
        mView = view;
    }

    @NonNull
    public View getView() {
        return mView;
    }

    public abstract void resume();
    public abstract void pause();
    public abstract void destroy();
}
