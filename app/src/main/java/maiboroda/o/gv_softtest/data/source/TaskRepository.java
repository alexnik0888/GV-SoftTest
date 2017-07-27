package maiboroda.o.gv_softtest.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import maiboroda.o.gv_softtest.data.Task;
import rx.Observable;

public class TaskRepository implements TaskDataSource {

    @Nullable
    private static TaskRepository instance = null;

    @NonNull
    private final TaskDataSource taskRemoteDataSource;
    @NonNull
    private final TaskDataSource taskLocalDataSource;

    private TaskRepository(@NonNull TaskDataSource taskRemoteDataSource, @NonNull TaskDataSource taskLocalDataSource) {
        this.taskRemoteDataSource = taskRemoteDataSource;
        this.taskLocalDataSource = taskLocalDataSource;
    }

    public static TaskRepository getInstance(@NonNull TaskDataSource tasksRemoteDataSource,
                                             @NonNull TaskDataSource tasksLocalDataSource) {
        if (instance == null) {
            instance = new TaskRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return instance;
    }

    @Override
    public Observable<Task> getTask() {
        return Observable.concat(taskLocalDataSource.getTask(), getAndSaveRemoteTask())
                .filter(task -> task != null)
                .first();
    }

    @Override
    public void saveTask(Task task) {
        taskLocalDataSource.saveTask(task);
    }

    private Observable<Task> getAndSaveRemoteTask() {
        return taskRemoteDataSource.getTask().doOnNext(taskLocalDataSource::saveTask);
    }
}
