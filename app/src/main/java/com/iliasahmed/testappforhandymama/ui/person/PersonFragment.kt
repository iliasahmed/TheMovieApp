package com.iliasahmed.testappforhandymama.ui.person

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.model.network.MovieModel
import com.iliasahmed.testappforhandymama.model.network.PersonModel
import com.iliasahmed.testappforhandymama.ui.MovieAdapter
import com.iliasahmed.testappforhandymama.ui.popular.MovieViewModel
import com.iliasahmed.testappforhandymama.utils.CommonUtils
import com.iliasahmed.testappforhandymama.utils.RecyclerViewItemDecorator
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_person.*


class PersonFragment : Fragment() {

    lateinit var movieViewModel: MovieViewModel
    var pd: ProgressDialog? = null
    lateinit var adapter: PersonAdapter
    lateinit var personList: List<PersonModel>

    private lateinit var glm: GridLayoutManager

    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var page = 1
    private var totalItem: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personList = ArrayList()

        initRecyclerView()

        movieViewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)

        carPersonApi(page)

        movieViewModel.personLiveData.observe(this, Observer<List<PersonModel>> {
            hideLoading()
            Logger.d(it.size)
            totalItem = movieViewModel.totalCount
            (personList as ArrayList<PersonModel>).addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView() {
        glm = GridLayoutManager(context, 2)
        rvPerson.layoutManager = glm
        adapter = PersonAdapter(personList, this.activity!!)
        rvPerson.addItemDecoration(RecyclerViewItemDecorator(2, RecyclerViewItemDecorator.dpToPx(context!!, 10), true))
        rvPerson.adapter = adapter
        rvPerson.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                            carPersonApi(page)
                        }
                    }
                }
            }
        })
    }


    private fun carPersonApi(page: Int) {
        showLoading()
        movieViewModel.fetchPersonList(page)
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
