package maiboroda.o.gv_softtest.main;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import maiboroda.o.gv_softtest.App;
import maiboroda.o.gv_softtest.R;
import maiboroda.o.gv_softtest.data.Task;

public class MainActivity extends AppCompatActivity implements MainContract.MainView,
        ImageAdapter.OnImageClickListener {

    @BindView(R.id.solve_until)
    TextView solveUntil;
    @BindView(R.id.created_date)
    TextView created;
    @BindView(R.id.registered_date)
    TextView registered;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.task_head)
    TextView head;
    @BindView(R.id.responsible)
    TextView responsible;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.image_recycler_view)
    RecyclerView recycler;

    @Inject
    MainContract.MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        presenter.getTask();
    }

    public void onViewClick(View view) {
        Snackbar.make(view, view.getClass().getSimpleName(), BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void setInfo(Task task) {
        solveUntil.setText(task.getSolveUntil());
        head.setText(task.getTitle());
        created.setText(task.getCreated());
        registered.setText(task.getRegistered());
        description.setText(task.getDescription());
        responsible.setText(task.getResponsible());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new ImageAdapter(task.getUrlArray(), this));
        state.setText(task.getState());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCompleted() {
        Toast.makeText(this, "Task loaded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(View v) {
        onViewClick(v);
    }
}
