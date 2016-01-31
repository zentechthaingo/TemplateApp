package sample.template.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;
import rx.Subscriber;
import sample.template.MockAppSchedulers;
import sample.template.domain.interactor.ThreeSecondsOperation;
import sample.template.presentation.contract.StubContract;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Tom Koptel
 */
public class StubPresenterTest {
    @Mock
    StubContract.View mView;

    ThreeSecondsOperation mThreeSecondsOperation;
    Void fakeVoidVoid = null;

    private StubPresenter mStubPresenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        mThreeSecondsOperation = spy(new ThreeSecondsOperation(new MockAppSchedulers()));
        when(mThreeSecondsOperation.buildUseCaseObservable(any(Void.class)))
                .thenReturn(Observable.<Void>just(null));

        mStubPresenter = new StubPresenter(mThreeSecondsOperation);
        mStubPresenter.injectView(mView);
    }

    @Test
    public void testLoadData() throws Exception {
        mStubPresenter.loadData();
        verify(mThreeSecondsOperation).execute(eq(fakeVoidVoid), any(Subscriber.class));
        verify(mView).showResult("After 3 seconds");
    }
}