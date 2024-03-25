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
import com.normal.studentapp.databinding.FragmentCarListBinding
import com.normal.studentapp.viewmodel.CarViewModel

class CarListFragment : Fragment() {
    private lateinit var bind: FragmentCarListBinding
    private lateinit var viewModel: CarViewModel
    private var carListAdapter = CarListAdapter(ArrayList())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentCarListBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        bind.recyclerView.adapter = carListAdapter

        observeViewModel()

        bind.refreshLayout.setOnRefreshListener {
            bind.recyclerView.visibility = View.GONE
            bind.txtError.visibility = View.GONE
            bind.loadingBar.visibility = View.VISIBLE
            viewModel.refresh()
            bind.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.carsLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                bind.recyclerView.visibility = View.GONE
                bind.loadingBar.visibility = View.VISIBLE
            } else{
                bind.recyclerView.visibility = View.VISIBLE
                bind.loadingBar.visibility = View.GONE
            }
        })

        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                bind.txtError?.visibility = View.VISIBLE
            } else {
                bind.txtError?.visibility = View.GONE
            }

        })
    }
}