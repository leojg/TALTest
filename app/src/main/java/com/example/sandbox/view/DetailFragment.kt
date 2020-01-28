package com.example.sandbox.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sandbox.GlideApp
import com.example.sandbox.R
import com.example.sandbox.databinding.FragmentDetailBinding
import com.example.sandbox.domain.Employee

class DetailFragment: Fragment() {

    lateinit var binding: FragmentDetailBinding

    private lateinit var employee: Employee

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail, container, false)

        arguments?.let {
            employee = it.getSerializable(EMPLOYEE_BUNDLE) as Employee
        } ?: run {
            //finish?
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            GlideApp.with(this@DetailFragment).load(employee.picture).into(employeePicture)
            employeeName.text = employee.name
            employeeDOB.text = employee.dob
            employeeAddress.text = employee.address
            employeeEmail.text = employee.email
            employeePassword.text = employee.password
            employeePhone.text = employee.phone
        }
    }

    companion object {

        const val EMPLOYEE_BUNDLE = "EMPLOYEE_BUNDLE"

        fun newInstance(employee: Employee): DetailFragment {

            val bundle = Bundle()
            bundle.putSerializable(EMPLOYEE_BUNDLE, employee)

            return DetailFragment().apply {
                arguments = bundle
            }
        }

    }

}