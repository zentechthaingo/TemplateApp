package sample.template.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Tom Koptel
 */
public class StubViewModel implements Parcelable {
    @NonNull
    private final String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    public StubViewModel(@NonNull String id) {
        this.id = id;
    }

    private StubViewModel(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<StubViewModel> CREATOR = new Parcelable.Creator<StubViewModel>() {
        public StubViewModel createFromParcel(Parcel source) {
            return new StubViewModel(source);
        }

        public StubViewModel[] newArray(int size) {
            return new StubViewModel[size];
        }
    };
}
