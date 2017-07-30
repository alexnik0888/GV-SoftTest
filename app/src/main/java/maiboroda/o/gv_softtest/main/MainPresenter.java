package maiboroda.o.gv_softtest.main;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import maiboroda.o.gv_softtest.data.source.TaskRepository;


class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView view;
    private TaskRepository repository;

    @Inject
    MainPresenter(MainContract.MainView view, TaskRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getTask() {
        repository.getTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(task -> view.setInfo(task),
                        throwable ->
                                view.showError(), () -> view.showCompleted());
    }
}
