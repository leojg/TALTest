package com.example.sandbox.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sandbox.R
import com.example.sandbox.adapter.EmployeeAdapter
import com.example.sandbox.databinding.FragmentMainBinding
import com.example.sandbox.domain.Employee
import com.example.sandbox.viewModel.MainViewModel

class MainFragment: Fragment() {

    lateinit var binding: FragmentMainBinding

    lateinit var viewModel: MainViewModel
    lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        adapter = EmployeeAdapter(object : ClickListener {
            override fun onItemClick(employee: Employee) {
                (activity as MainActivity).replaceFragment(DetailFragment.newInstance(employee))
            }
        })
        binding.employeeList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
    }

    override fun onResume() {
        super.onResume()
        subscribeToLiveData()
    }

    override fun onPause() {
        super.onPause()
        viewModel.resultLiveData.removeObservers(viewLifecycleOwner)
    }

    fun subscribeToLiveData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            if (it.first && it.second != null) {
                adapter.addItems(it.second!!)
            } else {
                //TODO: Show message
            }
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    interface ClickListener {
        fun onItemClick(employee: Employee)
    }

}