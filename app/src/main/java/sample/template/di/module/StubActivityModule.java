package sample.template.di.module;

import java.util.Collections;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import sample.template.domain.AppSchedulers;
import sample.template.data.route.FakeItemsSearchCallerFactory;
import sample.template.domain.model.AppItem;
import sample.template.domain.route.RouteCallerFactory;
import sample.template.domain.route.Router;
import sample.template.domain.route.page.ItemsLoader;
import sample.template.di.PerActivity;
import sample.template.presentation.model.mapper.ItemViewMapper;
import sample.template.presentation.presenter.StubPresenter;

/**
 * @author Tom Koptel
 */
@Module
public class StubActivityModule {
    @PerActivity
    @Provides
    StubPresenter providesPresenter(ItemViewMapper viewMapper, AppSchedulers appSchedulers) {
        RouteCallerFactory<List<AppItem>> fake = new FakeItemsSearchCallerFactory();
        List<RouteCallerFactory<List<AppItem>>> routeCallerFactories = Collections.singletonList(fake);

        Router<List<AppItem>> router = new Router<>(routeCallerFactories);
        ItemsLoader<List<AppItem>> itemsLoader = new ItemsLoader<>(router);

        return new StubPresenter(itemsLoader, viewMapper, appSchedulers);
    }
}
