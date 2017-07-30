package maiboroda.o.gv_softtest.data.source;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.local.TaskLocalDataSource;
import maiboroda.o.gv_softtest.data.source.remote.FakeTaskRemoteDataSource;

@Singleton
public class TaskRepository implements TaskDataSource {

    private final FakeTaskRemoteDataSource taskRemoteDataSource;
    private final TaskLocalDataSource taskLocalDataSource;

    @Inject
    public TaskRepository(FakeTaskRemoteDataSource taskRemoteDataSource, TaskLocalDataSource taskLocalDataSource) {
        this.taskRemoteDataSource = taskRemoteDataSource;
        this.taskLocalDataSource = taskLocalDataSource;
    }

    @Override
    public Observable<Task> getTask() {
        return Observable.concat(taskLocalDataSource.getTask(), getAndSaveRemoteTask())
                .filter(task -> task != null);
    }

    @Override
    public void saveTask(Task task) {
        taskLocalDataSource.saveTask(task);
    }

    private Observable<Task> getAndSaveRemoteTask() {
        return taskRemoteDataSource.getTask().doOnNext(taskLocalDataSource::saveTask);
    }
}
