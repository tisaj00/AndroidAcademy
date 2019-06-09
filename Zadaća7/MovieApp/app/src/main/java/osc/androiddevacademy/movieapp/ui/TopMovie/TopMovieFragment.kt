package osc.androiddevacademy.movieapp.ui.TopMovies

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
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.TopMoviePresenter
import osc.androiddevacademy.movieapp.ui.Movies.adapters.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.Movies.fragments.MoviesPagerFragment
import osc.androiddevacademy.movieapp.ui.TopMovie.TopMovieContract

class TopMovieFragment : Fragment(), TopMovieContract.View{


    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }

    private val presenter: TopMoviePresenter by lazy { TopMoviePresenter(BackendFactory.getMovieInteractor()) }

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
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }

        presenter.getTopMovies()
    }

    override fun onResume() {
        super.onResume()
        presenter.getTopMovies()
    }

    private fun onFavoriteClicked(movie: Movie) {
        presenter.favoriteMovie(movie)
        refreshMovies()
    }

    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(
                presenter.returnTopMovies(),
                movie
            ),
            true
        )
    }
    private fun refreshMovies(){
        gridAdapter.notifyDataSetChanged()

    }

    override fun onReturnTopMovieSuccess(movie: ArrayList<Movie>) {
        gridAdapter.setMovies(movie)
        gridAdapter.notifyDataSetChanged()
    }

    override fun onReturnTopMovieFailed() {
        Toast.makeText(App.getAppContext(),"Failed!",Toast.LENGTH_SHORT).show()
    }
}