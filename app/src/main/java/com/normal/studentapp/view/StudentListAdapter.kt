package com.normal.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.normal.studentapp.databinding.StudentListItemBinding
import com.normal.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonClickListener {
    class StudentViewHolder(var binding: StudentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        with(holder.binding){
//            txtId.text= studentList[position].id
//            txtName.text= studentList[position].name
//
//            btnDetail.setOnClickListener {
//                val id = studentList[position].id.toString()
//                val action = StudentListFragmentDirections.ActionDetailFragment(id)
//                Navigation.findNavController(it).navigate(action)
//            }
//
//            val picasso = Picasso.Builder(holder.itemView.context)
//            picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
//            picasso.build().load(studentList[position].url).into(imgStudent, object:Callback{
//                override fun onSuccess() {
//                    holder.binding.progressImg.visibility = View.INVISIBLE
//                    holder.binding.imgStudent.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            })
//        }
        holder.binding.student = studentList[position]
        Log.d("student", holder.binding.student!!.id.toString())
        holder.binding.listener = this
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = StudentListFragmentDirections.actionStudentListFragmentToDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }

}