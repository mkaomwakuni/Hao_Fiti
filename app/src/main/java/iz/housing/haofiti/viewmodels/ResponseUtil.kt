package iz.housing.haofiti.viewmodels

sealed class ResponseUtil<T>(val data: T? = null, val message: String? = null) {
    class Error<T>(message: String?) : ResponseUtil<T>(message = message)
    class Success<T>(data: T?) :ResponseUtil<T>(data = data)
}