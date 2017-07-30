package maiboroda.o.gv_softtest.data.source;


import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;
import maiboroda.o.gv_softtest.di.AppComponent;
import maiboroda.o.gv_softtest.di.AppModule;

@Singleton
@Subcomponent(modules = {TaskRepositoryModule.class})
public interface TaskRepositoryComponent {

    TaskRepository getTaskRepository();
}
