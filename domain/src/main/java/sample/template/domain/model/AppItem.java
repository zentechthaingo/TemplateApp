package sample.template.domain.model;


import org.jetbrains.annotations.NotNull;

/**
 * @author Tom Koptel
 */
public class AppItem {
    @NotNull
    private final String label;

    public AppItem(@NotNull String label) {
        this.label = label;
    }

    @NotNull
    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppItem appItem = (AppItem) o;

        return label.equals(appItem.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return "AppItem{" +
                "label='" + label + '\'' +
                '}';
    }
}
