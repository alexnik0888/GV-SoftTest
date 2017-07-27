package maiboroda.o.gv_softtest.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskRepository;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Mock
    MainContract.MainView mockView;
    @Mock
    TaskRepository repository;
    private MainContract.MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new MainPresenter(mockView, repository);

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(fun -> Schedulers.trampoline());
    }

    @Test
    public void testTaskLoaded() throws Exception {
        when(repository.getTask()).thenReturn(Observable.create(e -> {
            e.onNext(new Task());
            e.onComplete();
        }));

        presenter.getTask();

        verify(mockView, times(1)).setInfo(anyObject());
        verify(mockView, times(1)).showCompleted();
        verify(mockView, never()).showError();
    }

    @Test
    public void testTaskError() throws Exception {
        when(repository.getTask()).thenReturn(Observable.create(e -> {
            if (!e.isDisposed()) {
                e.onError(new Throwable());
            }
        }));

        presenter.getTask();

        verify(mockView, never()).setInfo(anyObject());
        verify(mockView, never()).showCompleted();
        verify(mockView, times(1)).showError();
    }


}