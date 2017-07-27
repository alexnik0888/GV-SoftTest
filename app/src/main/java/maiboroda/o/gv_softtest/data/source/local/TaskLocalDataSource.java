package maiboroda.o.gv_softtest.data.source.local;

import android.annotation.TargetApi;
import android.os.Build;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.internal.ManagableObject;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskDataSource;
import rx.Observable;



public class TaskLocalDataSource implements TaskDataSource {

    private static TaskLocalDataSource instance = null;

    private TaskLocalDataSource() {
    }

    public static TaskLocalDataSource getInstance() {
        if (instance == null) {
            instance = new TaskLocalDataSource();
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public Observable<Task> getTask() {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            return Observable.from(realmInstance.copyFromRealm(realmInstance.where(Task.class).findAllAsync()));
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void saveTask(Task task){
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((realm) -> realm.insertOrUpdate(task));
        }
    }
}
