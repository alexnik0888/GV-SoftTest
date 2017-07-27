package maiboroda.o.gv_softtest.data.source.local;

import android.os.Build;

import java.util.List;

import io.realm.Realm;
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

    @Override
    public Observable<Task> getTask() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try (Realm realmInstance = Realm.getDefaultInstance()) {
                return Observable.from(realmInstance.copyFromRealm(realmInstance.where(Task.class).findAllAsync()));
            }
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Task> list = realm.copyFromRealm(realm.where(Task.class).findAllAsync());
        realm.commitTransaction();
        return Observable.from(list);
    }

    public void saveTask(Task task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try (Realm realmInstance = Realm.getDefaultInstance()) {
                realmInstance.executeTransaction((realm) -> realm.insertOrUpdate(task));
            }
            return;
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(task);
        realm.commitTransaction();
    }
}
