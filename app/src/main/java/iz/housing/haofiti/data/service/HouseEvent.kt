package iz.housing.haofiti.data.service

import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.PropertyType

sealed class HouseEvent {
    data class SearchBarClicked(val searchQuery: String) : HouseEvent()
    data class OnCardClicked(val property: PropertyItem) : HouseEvent()
    data class OnCategoryClicked(val category: PropertyType) : HouseEvent()
}