package com.normal.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.normal.studentapp.R
import com.normal.studentapp.databinding.FragmentStudentListBinding
import com.normal.studentapp.viewmodel.ListViewModel


class StudentListFragment : Fragment() {
    private lateinit var bind: FragmentStudentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var studentListAdapter: StudentListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentStudentListBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentListAdapter = StudentListAdapter(ArrayList())
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        bind.recyclerView.adapter = studentListAdapter

        observeViewModel()

        bind.refreshLayout.setOnRefreshListener {
            bind.recyclerView.visibility = View.GONE
            bind.txtError.visibility = View.GONE
            bind.progressBar3.visibility = View.VISIBLE
            viewModel.refresh()
            bind.refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                bind.recyclerView.visibility = View.GONE
                bind.progressBar3.visibility = View.VISIBLE
            } else{
                bind.recyclerView.visibility = View.VISIBLE
                bind.progressBar3.visibility = View.GONE
            }
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                bind.txtError?.visibility = View.VISIBLE
            } else {
                bind.txtError?.visibility = View.GONE
            }

        })
    }
}