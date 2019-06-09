package osc.androiddevacademy.movieapp.ui.Favorites.fragments

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
import osc.androiddevacademy.movieapp.presentation.FavoritePresenter
import osc.androiddevacademy.movieapp.ui.Favorite.contract.FavoriteContract
import osc.androiddevacademy.movieapp.ui.Movies.adapters.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.Movies.fragments.MoviesPagerFragment

class FavoritesFragment: Fragment(), FavoriteContract.View{


    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }

    private val presenter: FavoriteContract.Presenter by lazy { FavoritePresenter(BackendFactory.getMovieInteractor()) }

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

        presenter.getFavoriteMovie()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMovie()
    }


    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(
                presenter.returnFavoriteMovie(),
                movie
            ),
            true
        )
    }

    private fun onFavoriteClicked(movie: Movie) {
        Toast.makeText(App.getAppContext(),"You can't favorite ${movie.title} , this film is already favorites", Toast.LENGTH_SHORT).show()
    }

    override fun onReturnSuccess(movies: ArrayList<Movie>) {
        gridAdapter.setMovies(movies)
        gridAdapter.notifyDataSetChanged()
    }

    override fun onReturnFailed() {
        Toast.makeText(App.getAppContext(),"Failed! You don't have favorite movie. Go to favorite and come back", Toast.LENGTH_SHORT).show()
    }

}