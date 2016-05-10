package sample.template.presentation.presenter;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Tom Koptel
 */
public abstract class Presenter<View> {
    @NonNull
    private View view;

    private final View nullView;

    protected Presenter(Class<View> viewType) {
        this.nullView = createNullView(viewType);
    }

    @SuppressWarnings("unchecked")
    protected View createNullView(Class<View> viewType) {
        return (View) Proxy.newProxyInstance(viewType.getClassLoader(), new Class[]{viewType}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }

    public final void takeView(@NonNull View view) {
        this.view = view;
        onBindView(view);
    }

    public final void dropView() {
        view = nullView;
        onUnbindView();
    }

    @NonNull
    public View getView() {
        return view;
    }

    public void onBindView(View view) {
    }

    public void onUnbindView() {
    }
}
