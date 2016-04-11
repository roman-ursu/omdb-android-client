package com.perfectial.omdb.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.perfectial.omdb.OMDBApp;
import com.perfectial.omdb.R;
import com.perfectial.omdb.net.bean.OpenDBMovieEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @Inject
    SearchPresenter searchPresenter;

    @Bind(R.id.tv_empty_text_view)
    TextView emptyTextView;

    @Bind(R.id.rv_list)
    RecyclerView list;

    @Bind(R.id.pb_preloader)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OMDBApp.getAppComponent().inject(this);
        searchPresenter.setSearchView(this);
        searchPresenter.onViewCreated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        searchPresenter.setSearchView(null);
        super.onDestroy();
    }

    @Override
    public void showPreloader() {
        emptyTextView.setVisibility(View.INVISIBLE);
        list.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovies(List<OpenDBMovieEntity> movies) {
        if (movies.size() <= 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            list.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);

        } else {
            emptyTextView.setVisibility(View.INVISIBLE);
            list.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);

            populateListWithData(movies);
        }
    }

    private void populateListWithData(List<OpenDBMovieEntity> movies) {
        if (list.getAdapter() == null) {
            // TODO: 11.04.16

        } else {
            MoviesAdapter moviesAdapter = (MoviesAdapter) list.getAdapter();
            moviesAdapter.setData(movies);
            moviesAdapter.notifyDataSetChanged();
        }
    }
}


class MoviesAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<OpenDBMovieEntity> data = new ArrayList<>();

    public void setData(List<OpenDBMovieEntity> data) {
        data.clear();
        data.addAll(data);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class MovieHolder extends RecyclerView.ViewHolder {

    public MovieHolder(View itemView) {
        super(itemView);
    }
}