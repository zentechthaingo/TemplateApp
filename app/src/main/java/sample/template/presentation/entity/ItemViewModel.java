package sample.template.presentation.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Tom Koptel
 */
public class ItemViewModel implements Parcelable {
    @NonNull
    private final String label;

    public ItemViewModel(@NonNull String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemViewModel that = (ItemViewModel) o;

        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
    }

    protected ItemViewModel(Parcel in) {
        this.label = in.readString();
    }

    public static final Creator<ItemViewModel> CREATOR = new Creator<ItemViewModel>() {
        public ItemViewModel createFromParcel(Parcel source) {
            return new ItemViewModel(source);
        }

        public ItemViewModel[] newArray(int size) {
            return new ItemViewModel[size];
        }
    };
}
