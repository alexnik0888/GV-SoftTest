package maiboroda.o.gv_softtest.main;

import javax.inject.Singleton;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = MainModule.class)
@Singleton
public interface MainComponent  extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
