package com.normal.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.normal.studentapp.databinding.CarListItemBinding
import com.normal.studentapp.databinding.StudentListItemBinding
import com.normal.studentapp.model.Car
import com.normal.studentapp.model.Student

class CarListAdapter(val carList: ArrayList<Car>): RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(var binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        with(holder.binding){
            txtName.text = carList[position].name
            txtColors.text = carList[position].colors.joinToString(", ")
            txtManufacture.text = carList[position].manufacturer.name + ", " + carList[position].manufacturer.country
        }
    }

    fun updateCarList(newCarList: ArrayList<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }
}