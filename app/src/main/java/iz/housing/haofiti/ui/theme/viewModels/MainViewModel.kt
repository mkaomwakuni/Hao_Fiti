package iz.housing.haofiti.ui.theme.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.model.HouseDataState
import iz.housing.haofiti.data.repository.HouseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val houseRepository: HouseRepository
) : ViewModel() {

    val response: MutableState<HouseDataState> = mutableStateOf(HouseDataState.Empty)

    init {
        fetchHouses()
    }

    private fun fetchHouses() {
        viewModelScope.launch {
            houseRepository.fetchHouses().collect { state ->
                response.value = state
            }
        }
    }
}
