package maiboroda.o.gv_softtest.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import maiboroda.o.gv_softtest.main.MainComponent;

@Module(subcomponents = {
        MainComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
