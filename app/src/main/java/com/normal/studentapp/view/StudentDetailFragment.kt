package com.normal.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.normal.studentapp.R
import com.normal.studentapp.databinding.FragmentStudentDetailBinding
import com.normal.studentapp.databinding.FragmentStudentListBinding
import com.normal.studentapp.viewmodel.DetailViewModel
import com.normal.studentapp.viewmodel.ListViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var bind: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentStudentDetailBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val sid = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(sid)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it
            bind.txtId.setText(it.id)
            bind.txtName.setText(it.name)
            bind.txtBirth.setText(it.dob)
            bind.txtPhone.setText(it.phone)

            val picasso = Picasso.Builder(bind.root.context)
            picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
            picasso.build().load(it.url).into(bind.imgStudent)

            bind.btnUpdate?.setOnClickListener{
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "Five seconds")
                        MainActivity.showNotif(student.name.toString(), "A new notification created", R.drawable.baseline_person_24)
                    }
            }
        })
    }


}