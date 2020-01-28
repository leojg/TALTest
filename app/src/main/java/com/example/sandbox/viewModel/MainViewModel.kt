package com.example.sandbox.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox.domain.Employee
import com.example.sandbox.repository.RandomUserApi
import com.example.sandbox.repository.RandomUserObject
import com.example.sandbox.repository.RandomUserRepository
import com.example.sandbox.repository.getRetrofitService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var resultLiveData = MutableLiveData<Pair<Boolean, List<Employee>?>>()
    var repository = RandomUserRepository(getRetrofitService<RandomUserApi>())


    fun getUsers() {
        viewModelScope.launch {
            repository.getEmployees()?.let {

                val results = mutableListOf<Employee>()

                it.forEach {user ->

                    val fullName = if  (!user.name["first"].isNullOrEmpty())
                        StringBuilder(user.name["first"]!!).append(" ").append(user.name["last"]).toString()
                    else ""
                    val picture = user.picture["medium"]
                    val dob = user.dob["date"]
                    val email = user.email
                    val phone = user.phone

                    val address = if (!user.location.street.isEmpty()) {
                        StringBuilder(user.location.street["name"]!! as String).append(" ").append(user.location.street["number"]!!.toString()).toString()
                    } else ""

                    val password = user.login["password"]
                    val username = user.login["username"]


                    val employee = Employee(name=fullName, picture=picture, dob=dob, email=email,
                        phone=phone, address=address, username=username, password=password)
                    results.add(employee)
                }

                resultLiveData.postValue(true to results)
            } ?: run {
                resultLiveData.postValue(false to null)
            }
        }

    }

}