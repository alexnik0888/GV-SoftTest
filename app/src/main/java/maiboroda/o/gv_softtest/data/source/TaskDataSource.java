package maiboroda.o.gv_softtest.data.source;

import io.reactivex.Observable;
import maiboroda.o.gv_softtest.data.Task;

public interface TaskDataSource {
    Observable<Task> getTask();

    void saveTask(Task task);
}
