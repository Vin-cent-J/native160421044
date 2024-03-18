package com.normal.studentapp.view

import android.os.Bundle
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
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            bind.txtId.setText(it.id)
            bind.txtName.setText(it.name)
            bind.txtBirth.setText(it.dob)
            bind.txtPhone.setText(it.phone)
        })
    }


}