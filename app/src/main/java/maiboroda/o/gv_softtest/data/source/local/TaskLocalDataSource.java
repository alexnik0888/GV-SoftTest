package maiboroda.o.gv_softtest.data.source.local;

import android.content.Context;
import android.os.Build;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;
import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskDataSource;

@Singleton
public class TaskLocalDataSource implements TaskDataSource {

    @Inject
    public TaskLocalDataSource() {
    }

    @Override
    public Observable<Task> getTask() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try (Realm realmInstance = Realm.getDefaultInstance()) {
                return Observable.fromIterable(realmInstance.copyFromRealm(realmInstance.where(Task.class).findAllAsync()));
            }
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Task> list = realm.copyFromRealm(realm.where(Task.class).findAllAsync());
        realm.commitTransaction();
        return Observable.fromIterable(list);
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
