package sample.template.presentation.contract;

/**
 * @author Tom Koptel
 */
public interface StubContract {
    interface View {
        void showLoading();
        void hideLoading();
    }

    interface Action {
        void loadData();
    }
}
