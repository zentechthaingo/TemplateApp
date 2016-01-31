package sample.template.presentation.contract;

import android.support.annotation.NonNull;

import java.util.List;

import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
public interface StubContract {
    interface View {
        void showResult(@NonNull List<ItemViewModel> result);
    }

    interface Action {
        void loadData();
    }
}
