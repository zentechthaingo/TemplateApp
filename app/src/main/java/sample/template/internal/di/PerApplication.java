package sample.template.internal.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Tom Koptel
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}
