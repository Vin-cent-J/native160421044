package com.normal.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.normal.studentapp.R
import com.normal.studentapp.databinding.FragmentDetailBinding
import com.normal.studentapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class DetailFragment : Fragment(), ButtonClickListener {
    private lateinit var bind: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val id = DetailFragmentArgs.fromBundle(requireArguments()).studentid
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(id)
        }

        bind.listener = this

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            bind.student = it
            Log.d("urlimg", it.url.toString())
        })
    }

    override fun onButtonClick(v: View) {
        val name = bind.student!!.name.toString()
        Observable.timer(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "Five seconds")
                MainActivity.showNotif(name, "A new notification created", R.drawable.baseline_person_24)
            }
    }

}