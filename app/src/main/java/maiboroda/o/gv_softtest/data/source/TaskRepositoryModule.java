package maiboroda.o.gv_softtest.data.source;

import dagger.Module;
import dagger.Provides;
import maiboroda.o.gv_softtest.data.source.local.TaskLocalDataSource;
import maiboroda.o.gv_softtest.data.source.remote.FakeTaskRemoteDataSource;

@Module
public class TaskRepositoryModule {

    @Provides
    TaskLocalDataSource provideTasksLocalDataSource() {
        return new TaskLocalDataSource();
    }

    @Provides
    FakeTaskRemoteDataSource provideTasksRemoteDataSource() {
        return new FakeTaskRemoteDataSource();
    }
}
