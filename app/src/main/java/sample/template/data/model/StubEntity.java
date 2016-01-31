package sample.template.data.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

/**
 * @author Tom Koptel
 */
public class StubEntity {
    @Expose
    @NonNull
    private String id;

    @NonNull
    public String getId() {
        return id;
    }
}
