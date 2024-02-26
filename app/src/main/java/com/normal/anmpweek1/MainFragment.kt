package com.normal.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.normal.anmpweek1.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var bind: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentMainBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.btnStart.setOnClickListener {
            val player = bind.txtName.text.toString()
            val action = MainFragmentDirections.actionGameFragment(player)
            Navigation.findNavController(it).navigate(action)
        }
    }
}