package sample.template.presentation.contract;

import android.support.annotation.NonNull;

import java.util.List;

import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public interface StubContract {
    interface View {
        void showFirstPage(@NonNull List<ItemViewModel> result);
        void showOlderPage(@NonNull List<ItemViewModel> result);
        void showLoader();
        void hideLoader();
        void showError(String error);
    }

    interface Action {
        void loadFirstPage();
        void loadOlderPage();
    }

    interface Model {
        void bind(Callback callback);
        void unbind();

        void loadFirstPage();
        void loadOlderPage();

        interface Callback {
            void onFirstPageLoaded(@NonNull List<ItemViewModel> items);

            void onOlderPageLoaded(@NonNull List<ItemViewModel> items);

            void onFirstPageError(@NonNull Throwable throwable);

            void onOlderPageError(@NonNull Throwable throwable);
        }
    }
}
