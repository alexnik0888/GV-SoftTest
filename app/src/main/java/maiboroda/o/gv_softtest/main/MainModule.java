package maiboroda.o.gv_softtest.main;


import dagger.Module;
import dagger.Provides;
import maiboroda.o.gv_softtest.data.source.TaskRepository;

@Module
public class MainModule {

    @Provides
    MainContract.MainView provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    MainContract.MainPresenter provideMainPresenter(MainContract.MainView mainView, TaskRepository repository){
        return new MainPresenter(mainView, repository);
    }
}
