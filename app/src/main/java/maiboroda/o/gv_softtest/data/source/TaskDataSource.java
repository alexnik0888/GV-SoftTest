package maiboroda.o.gv_softtest.data.source;

import maiboroda.o.gv_softtest.data.Task;
import rx.Observable;

public interface TaskDataSource {
    Observable<Task> getTask();

    void saveTask(Task task);
}
