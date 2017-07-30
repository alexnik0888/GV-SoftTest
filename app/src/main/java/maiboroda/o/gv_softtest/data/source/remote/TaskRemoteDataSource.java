package maiboroda.o.gv_softtest.data.source.remote;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskDataSource;
import maiboroda.o.gv_softtest.util.RetrofitUtil;

@Singleton
public class TaskRemoteDataSource implements TaskDataSource {

    @Inject
    public TaskRemoteDataSource() {
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
