package com.iliasahmed.testappforhandymama.ui.popular

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.model.network.GenreModel
import com.iliasahmed.testappforhandymama.model.network.MovieModel
import com.iliasahmed.testappforhandymama.ui.MovieAdapter
import com.iliasahmed.testappforhandymama.utils.CommonUtils
import com.iliasahmed.testappforhandymama.utils.RecyclerViewItemDecorator
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : GenreAdapter.OnGenreItemClickedListener<GenreModel>, Fragment() {

    lateinit var movieViewModel: MovieViewModel
    var pd: ProgressDialog? = null
    lateinit var adapter: MovieAdapter
    lateinit var popularMovieList: List<MovieModel>
    lateinit var genreList: List<GenreModel>

    private lateinit var glm: GridLayoutManager

    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var page = 1
    private var totalItem: Int = 0
    private var isSearch: Boolean = false
    private var isGenreSelected = false
    private var searchQuery: String = ""
    private var genreId: Int = 0
    private var selectedId: Int = 0

    var genreView: View? = null
    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        popularMovieList = ArrayList()

        initRecyclerView()

        movieViewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)

        callGenreApi()
        callApi(page)

        movieViewModel.movieLiveData.observe(this, Observer<List<MovieModel>> {
            hideLoading()
            Logger.d(it.size)
            totalItem = movieViewModel.totalCount
            (popularMovieList as ArrayList<MovieModel>).addAll(it)
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener(View.OnClickListener {
            genreView = LayoutInflater.from(context).inflate(R.layout.genre_filter_view, null, false)
            var builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setView(genreView)
            var rvGenre: RecyclerView = genreView!!.findViewById(R.id.rvGenre)
            rvGenre.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            rvGenre.addItemDecoration(RecyclerViewItemDecorator(2, 10, true))
            var adapter = GenreAdapter(genreList, activity!!, this, genreId)
            rvGenre.adapter = adapter
            alertDialog = builder.create()
            alertDialog!!.show()
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.length > 2) {
                    isSearch = true
                    isGenreSelected = false
                    searchQuery = query
                    page = 1
                    (popularMovieList as ArrayList<MovieModel>).clear()
                    callSearchApi(page, searchQuery)
                } else if (query == null || query.length == 0) {
                    isSearch = false
                    isGenreSelected = false
                    page = 1
                    (popularMovieList as ArrayList<MovieModel>).clear()
                    callApi(page)
                } else {
                    isSearch = false
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 2) {
                    isSearch = true
                    isGenreSelected = false
                    searchQuery = newText
                    page = 1
                    (popularMovieList as ArrayList<MovieModel>).clear()
                    callSearchApi(page, searchQuery)
                } else if (newText.length == 0) {
                    isSearch = false
                    isGenreSelected = false
                    page = 1
                    (popularMovieList as ArrayList<MovieModel>).clear()
                    callApi(page)
                } else {
                    isSearch = false
                }
                return false
            }
        })
    }

    private fun callGenreApi() {
        movieViewModel.fetchGenreList()
        movieViewModel.genreLiveData.observe(this, Observer<List<GenreModel>> {
            genreList = it
        })
    }

    private fun initRecyclerView() {
        glm = GridLayoutManager(context, 2)
        rvPopularMovie.layoutManager = glm
        adapter = MovieAdapter(popularMovieList, this.activity!!)
        rvPopularMovie.addItemDecoration(RecyclerViewItemDecorator(2, RecyclerViewItemDecorator.dpToPx(context!!, 10), true))
        rvPopularMovie.adapter = adapter
        rvPopularMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = glm.childCount
                    totalItemCount = glm.itemCount
                    pastVisiblesItems = glm.findFirstVisibleItemPosition()

                    Logger.d("$visibleItemCount     $totalItemCount     $pastVisiblesItems");
                    if (totalItemCount < totalItem) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            page = page + 1;
                            if (isSearch) {
                                callSearchApi(page, searchQuery)
                            } else if (isGenreSelected) {
                                callByGenre(page, genreId)
                            } else {
                                callApi(page)
                            }

                        }
                    }
                }
            }
        })
    }

    override fun onGenreItemClicked(genre: GenreModel) {
        Logger.d(genre.name)
        alertDialog!!.dismiss()
        page = 1
        genreId = genre.id
        isGenreSelected = true

        (popularMovieList as ArrayList).clear()
        callByGenre(page, genre.id)
    }

    private fun callApi(page: Int) {
        showLoading()
        movieViewModel.fetchPopularMovie(page)
    }

    private fun callSearchApi(page: Int, query: String) {
        showLoading()
        movieViewModel.searchPopularMovie(page, query)
    }

    private fun callByGenre(page: Int, id: Int) {
        showLoading()
        movieViewModel.fetchMovieByGenre(page, id)
    }

    fun showLoading() {
        hideLoading()
        pd = CommonUtils.showLoadingDialog(context as Activity)
    }

    fun hideLoading() {
        if (pd != null && pd!!.isShowing) {
            pd!!.cancel()
        }
    }

}
