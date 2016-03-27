package sample.template.presentation.component.presenter;

public interface HasPresenter<P extends Presenter> {
    P getPresenter();
}
