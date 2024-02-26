package com.normal.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.normal.anmpweek1.databinding.FragmentGameoverBinding

class GameoverFragment : Fragment() {
    private lateinit var bind: FragmentGameoverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentGameoverBinding.inflate(layoutInflater)
        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val score = GameoverFragmentArgs.fromBundle(requireArguments()).score
            bind.txtScore.text = "Your score is $score"
        }

        bind.btnBack.setOnClickListener {
            val action = GameoverFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}