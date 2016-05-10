package sample.template.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Tom Koptel
 * @since 2.5
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerScreen {
}