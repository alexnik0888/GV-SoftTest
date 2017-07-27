package maiboroda.o.gv_softtest.main;

import maiboroda.o.gv_softtest.data.Task;

interface MainContract {
    interface MainView {
        void setInfo(Task task);

        void showError();

        void showCompleted();
    }

    interface MainPresenter {
        void getTask();
    }
}
