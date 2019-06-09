package osc.androiddevacademy.movieapp.ui.Movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.presentation.MoviesGridPresenter
import osc.androiddevacademy.movieapp.ui.Movies.adapters.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.Movies.contracts.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridFragment : Fragment(),MoviesGridContract.View {

    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }
    private val presenter:MoviesGridContract.Presenter by lazy { MoviesGridPresenter(BackendFactory.getMovieInteractor()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragemnt_movie_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context,SPAN_COUNT)
        }
        presenter.getPopularMovies()
    }

    override fun onResume() {
        super.onResume()
        presenter.getPopularMovies()
    }


    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(
                presenter.returnPopularMovies(),
                movie
            ),
            true
        )
    }

    private fun onFavoriteClicked(movie: Movie) {
        presenter.favoriteMovie(movie)
        refreshMovie()

    }

    private fun refreshMovie() {
        gridAdapter.notifyDataSetChanged()
    }

    override fun onReturnMoviesSuccess(movies: ArrayList<Movie>) {
        gridAdapter.setMovies(movies)
        gridAdapter.notifyDataSetChanged()
    }

    override fun onReturnMoviesFailed() {
        Toast.makeText(App.getAppContext(),"Failed!",Toast.LENGTH_SHORT).show()
    }

}