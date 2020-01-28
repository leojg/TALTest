package com.example.sandbox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox.GlideApp
import com.example.sandbox.R
import com.example.sandbox.databinding.EmployeeItemBinding
import com.example.sandbox.domain.Employee
import com.example.sandbox.view.MainFragment

class EmployeeAdapter(val clickListener: MainFragment.ClickListener): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    val items = mutableListOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.employee_item, parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    fun addItems(employees: List<Employee>) {
        items.addAll(employees)
        notifyItemRangeInserted(items.size - employees.size, employees.size)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = items[position]

        with(holder.binding) {
            GlideApp.with(root).load(employee.picture).into(employeePicture)
            employeeName.text = employee.name

            //TODO: Format date
            employeeDOB.text = employee.dob

            root.setOnClickListener {
                clickListener.onItemClick(employee)
            }
        }

    }


    inner class EmployeeViewHolder(val binding: EmployeeItemBinding): RecyclerView.ViewHolder(binding.root)
}