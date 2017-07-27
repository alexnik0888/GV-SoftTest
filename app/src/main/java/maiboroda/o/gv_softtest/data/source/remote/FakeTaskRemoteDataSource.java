package maiboroda.o.gv_softtest.data.source.remote;

import java.util.Arrays;

import maiboroda.o.gv_softtest.data.Task;
import maiboroda.o.gv_softtest.data.source.TaskDataSource;
import rx.Observable;

public class FakeTaskRemoteDataSource implements TaskDataSource {
    private static FakeTaskRemoteDataSource instance = null;

    private FakeTaskRemoteDataSource() {
    }

    public static FakeTaskRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new FakeTaskRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<Task> getTask() {
        return Observable.just(new Task("Title", "Description",
                Arrays.asList("http://bm.img.com.ua/nxs/img/prikol/images/large/3/9/315193.jpg",
                        "http://cdn.fishki.net/upload/post/2017/03/19/2245758/tn/01-beautiful-white-cat-imagescar-wallpaper.jpg",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSddhPfzySYlw6zJ-ohdM50K4TXHeuHE5YGHLzR1YDOnlwKsTGy"),
                "Responsible", "date", "date", "date", "In process"));
    }

    @Override
    public void saveTask(Task task) {
        // no implementation
    }
}
