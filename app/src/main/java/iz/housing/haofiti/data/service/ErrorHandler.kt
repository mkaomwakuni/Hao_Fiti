package iz.housing.haofiti.data.service

sealed class  ResultState <out  T>{
    data class Success<T> (val data: T): ResultState<T>()

    data class Error(val exception: Exception) : ResultState<Nothing>()
}