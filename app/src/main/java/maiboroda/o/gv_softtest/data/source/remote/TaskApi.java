package maiboroda.o.gv_softtest.data.source.remote;

import io.reactivex.Observable;
import maiboroda.o.gv_softtest.data.Task;
import retrofit2.http.GET;

/**
 * Just example, there no real api
 */

public interface TaskApi {
    @GET("task")
    Observable<Task> getTask();
}
