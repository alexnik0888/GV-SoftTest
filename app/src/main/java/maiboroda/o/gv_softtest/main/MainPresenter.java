package maiboroda.o.gv_softtest.main;

import android.util.Log;

import maiboroda.o.gv_softtest.data.source.TaskRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView view;
    private TaskRepository repository;

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
                        throwable -> {
                            view.showError();
                            Log.d("getTaskError", throwable.getMessage());
                        }, () -> view.showCompleted());
    }
}
