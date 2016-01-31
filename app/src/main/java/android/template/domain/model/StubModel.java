package android.template.domain.model;

import android.support.annotation.NonNull;

/**
 * @author Tom Koptel
 */
public class StubModel {
    @NonNull
    private final String id;

    public StubModel(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }
}
