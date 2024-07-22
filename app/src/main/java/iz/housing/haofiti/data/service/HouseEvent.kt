package iz.housing.haofiti.data.service

import iz.housing.haofiti.ui.theme.presentation.explorer.House

sealed class HouseEvent {
    data class OnCardClicked(val house: House):HouseEvent()
    data class OnCategoryClicked(val house: House):HouseEvent()
}