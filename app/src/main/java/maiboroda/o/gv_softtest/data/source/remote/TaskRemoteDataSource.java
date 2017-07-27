package maiboroda.o.gv_softtest.data.source.remote;

import io.reactivex.Observable;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskDataSource;
import maiboroda.o.gv_softtest.util.RetrofitUtil;

public class TaskRemoteDataSource implements TaskDataSource {
    private static TaskRemoteDataSource instance = null;

    private TaskRemoteDataSource() {
    }

    public static TaskRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new TaskRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<Task> getTask() {
        TaskApi taskApi = RetrofitUtil.createApi();
        return taskApi.getTask();
    }

    @Override
    public void saveTask(Task task) {
        // no implementation
    }
}
