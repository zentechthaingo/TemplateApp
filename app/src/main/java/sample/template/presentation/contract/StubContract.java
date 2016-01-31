package sample.template.presentation.contract;

import android.support.annotation.NonNull;

/**
 * @author Tom Koptel
 */
public interface StubContract {
    interface View {
        void showResult(@NonNull String result);
    }

    interface Action {
        void loadData();
    }
}
