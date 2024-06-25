package com.salvatour

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvatour.data.api.RetrofitClient
import com.salvatour.data.model.PostModel
import com.salvatour.data.model.RegisterData
import com.salvatour.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    private val api = RetrofitClient.apiService


    private val _postTourState = MutableLiveData<UiState>()
    val postTourState: LiveData<UiState> get() = _postTourState

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    private val _registeryState = MutableStateFlow<UiState>(UiState.Ready)
    val RegisteryState: StateFlow<UiState> = _registeryState

    private val _loginState = MutableStateFlow<UiState>(UiState.Ready)
    val loginState: StateFlow<UiState> = _loginState

    private val _getUserState = MutableStateFlow<UiState>(UiState.Loading)
    val getUserState: StateFlow<UiState> = _getUserState

    private val _updateUserState = MutableLiveData<UiState>()
    val updateUserState: LiveData<UiState> get() = _updateUserState

    private val Alltours = MutableStateFlow(listOf<PostModel>())
    val allTours: StateFlow<List<PostModel>> = Alltours

    val authUser = MutableStateFlow<UserModel>(UserModel("","","","","","","","","","",""))

    private val ToursbyUser = MutableStateFlow(listOf<PostModel>())
    val toursbyUser: StateFlow<List<PostModel>> = ToursbyUser

    private val token = MutableStateFlow<String>("")
    val tokenState: StateFlow<String> = token

    private val selectedPost = MutableStateFlow<PostModel>(PostModel("","","","","","","","",""))
    val selectedPostState: StateFlow<PostModel> = selectedPost
    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }

    fun register(registerData: RegisterData) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.register(registerData)
                _registeryState.value = UiState.Success(response.token)
            } catch (e: Exception) {
                _registeryState.value = UiState.Error(400)
            }
        }
    }

    fun login(identify: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.login(identify, password)
                Log.i("MainViewModel", response.toString())
                val token = response.token
                _loginState.value = UiState.Success(token)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }

    fun whoami (token: String)  {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.whoami(token)
                Log.i("MainViewModel", response.toString())
                authUser.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }

    fun updateUser(token: String, user: UserModel) {
        viewModelScope.launch {
            try {
                val response_ = RetrofitClient.apiService.whoami(token)
                Log.i("MainViewModel", response_.toString())
                val response = RetrofitClient.apiService.updateUser(token, user)
                Log.i("MainViewModel", response.toString())

            } catch (e: Exception) {
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }

    fun postTour (token: String, postModel: PostModel) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.postTour(token, postModel)
                Log.i("MainViewModel", response.toString())
                _postTourState.value = UiState.Ready
            } catch (e: Exception) {
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }

    fun deleteTour ( id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.deleteTour(token = tokenState.value,id = id)
                Log.i("MainViewModel", response.toString())
                _postTourState.value = UiState.Ready
            }catch (e: Exception){
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }

    fun getByidUser (token: String, id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.findByUserTour(token, id)
                Log.i("MainViewModel", response.toString())
                ToursbyUser.value = response
            } catch (e: Exception) {
                Log.e("MainViewModel", "Login failed", e)
                _loginState.value = UiState.Error(400)
            }
        }
    }


}


sealed class UiState {

    data class Success(val token: String): UiState()
    data class Error(val code: Int) : UiState()
    data object Ready : UiState()
    data object Loading : UiState()

}