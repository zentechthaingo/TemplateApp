package sample.template.presentation.contract;

import android.support.annotation.NonNull;

import java.util.List;

import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public interface StubContract {
    interface View {
        void ready();
        void showFirstPage(@NonNull List<ItemViewModel> result);
        void showOlderPage(@NonNull List<ItemViewModel> result);
        void cancelPageLoader();
    }

    interface Action {
        void loadFirstPage();
        void loadOlderPage();
    }
}
