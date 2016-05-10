package sample.template.presentation.model;

/**
 * @author Tom Koptel
 * @since 2.5
 */
public interface Result<Data> {
    boolean isSuccess();
    Data getData();
}
