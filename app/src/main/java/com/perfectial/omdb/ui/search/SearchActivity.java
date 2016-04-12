package com.perfectial.omdb.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectial.omdb.OMDBApp;
import com.perfectial.omdb.R;
import com.perfectial.omdb.domain.bean.OpenDBMovie;
import com.squareup.picasso.Picasso;

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

        initList();

        searchPresenter.setSearchView(this);
        searchPresenter.onViewCreated();
    }

    private void initList() {
        MoviesAdapter moviesAdapter = new MoviesAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        list.setAdapter(moviesAdapter);
        list.setLayoutManager(linearLayoutManager);
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
    public void showMovies(List<OpenDBMovie> movies) {
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

    @Override
    public void showError(String errorMessage) {
        emptyTextView.setVisibility(View.VISIBLE);
        list.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
    }

    private void populateListWithData(List<OpenDBMovie> movies) {
        MoviesAdapter moviesAdapter = (MoviesAdapter) list.getAdapter();
        moviesAdapter.setData(movies);
        moviesAdapter.notifyDataSetChanged();
    }
}

class MoviesAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<OpenDBMovie> data = new ArrayList<>();

    public void setData(List<OpenDBMovie> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        OpenDBMovie movie = data.get(position);

        Picasso.with(holder.poster.getContext())
                .load(movie.getPoster())
                .into(holder.poster);

        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getType());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class MovieHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.iv_poster)
    ImageView poster;

    @Bind(R.id.tv_title)
    TextView title;

    @Bind(R.id.tv_type)
    TextView type;

    @Bind(R.id.tv_year)
    TextView year;

    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}