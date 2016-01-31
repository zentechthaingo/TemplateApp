package sample.template.data.cache;

/**
 * @author Tom Koptel
 */
public interface Cache<Target, Key> {
    Target put(Key key, Target target);
    Target get(Key key);
    Target remove(Key key);
}
