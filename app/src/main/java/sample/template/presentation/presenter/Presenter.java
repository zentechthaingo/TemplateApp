package sample.template.presentation.presenter;

/**
 * @author Tom Koptel
 */
public abstract class Presenter<View> {
    protected View mView;

    public void injectView(View view) {
        mView = view;
    }

    public abstract void resume();
    public abstract void pause();
    public abstract void destroy();
}
