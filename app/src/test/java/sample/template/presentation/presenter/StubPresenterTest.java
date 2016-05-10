package sample.template.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sample.template.presentation.model.ItemModel;

import static org.mockito.Mockito.verify;

/**
 * @author Tom Koptel
 * @since 2.5
 */
public class StubPresenterTest {

    @Mock
    ItemModel itemModel;

    private StubPresenter stubPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stubPresenter = new StubPresenter(itemModel);
    }

    @Test
    public void should_request_first_page_on_load_data() throws Exception {
        stubPresenter.loadData();
        verify(itemModel).loadData();
    }
}