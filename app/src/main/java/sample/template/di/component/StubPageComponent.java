package sample.template.di.component;

import dagger.Subcomponent;
import sample.template.di.PerPage;
import sample.template.di.module.LoaderModule;
import sample.template.di.module.StubPageModule;

/**
 * @author Tom Koptel
 */
@PerPage
@Subcomponent(
        modules = {
                StubPageModule.class,
        }
)
public interface StubPageComponent {
    StubActivityComponent plus(LoaderModule loaderModule);
}
