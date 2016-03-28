package sample.template.presentation.component.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BasePresenter<T> implements Presenter<T> {
    protected T view;

    @Override
    public void bindView(@NonNull T view) {
        if (view == null) {
            throw new IllegalArgumentException("View should not be null");
        }
        this.view = view;
        onBindView(view);
    }

    @Override
    public void onBindView(T view) {
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void onCreate(@Nullable PresenterBundle bundle) {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onSaveInstanceState(@NonNull PresenterBundle bundle) {
    }
}
