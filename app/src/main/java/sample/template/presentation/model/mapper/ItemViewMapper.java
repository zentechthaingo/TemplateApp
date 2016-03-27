package sample.template.presentation.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sample.template.domain.model.AppItem;
import sample.template.di.PerPage;
import sample.template.presentation.model.ItemViewModel;

/**
 * @author Tom Koptel
 */
@PerPage
public class ItemViewMapper {
    @Inject
    public ItemViewMapper() {
    }

    public List<ItemViewModel> toViewModels(List<AppItem> items) {
        List<ItemViewModel> list = new ArrayList<>(items.size());
        for (AppItem item : items) {
            if (item != null) {
                ItemViewModel viewModel = toViewModel(item);
                list.add(viewModel);
            }
        }
        return list;
    }

    private ItemViewModel toViewModel(AppItem item) {
        return new ItemViewModel(item.getLabel());
    }
}
