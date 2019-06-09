package osc.androiddevacademy.movieapp.ui.Movie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragemnt_details.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.MovieDetailsPresenter
import osc.androiddevacademy.movieapp.ui.Movie.contracts.MoviePagerContract
import osc.androiddevacademy.movieapp.ui.Review.adapters.ReviewAdapter


class MovieDetailsFragment : Fragment() , MoviePagerContract.View{

    private val reviewsAdapter by lazy { ReviewAdapter() }

    private val presenter: MovieDetailsPresenter by lazy {MovieDetailsPresenter(BackendFactory.getMovieInteractor()) }
    private val appDB by lazy { MoviesDatabase.getInstance(App.getAppContext()) }

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"

        fun getInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_EXTRA, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var movie: Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragemnt_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        movie = arguments?.getParcelable(MOVIE_EXTRA) as Movie
        initUi()
        presenter.returnMovie(movie)
        initListeners()
    }

    private fun initUi(){
        movieImage.loadImage(movie.poster)
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = movie.releaseDate
        movieVoteAverage.text = movie.averageVote.toString()

        movieReviewList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners(){
        movieFavorite.setOnClickListener {
            onFavoriteClicked()
        }
    }

    private fun onFavoriteClicked(){

    }

    override fun onReturnSuccess(rewiews: List<Review>) {
        reviewsAdapter.setReviewList(rewiews)
    }

    override fun onReturnFailed() {
       Toast.makeText(App.getAppContext(),"Failed",Toast.LENGTH_SHORT).show()
    }


}