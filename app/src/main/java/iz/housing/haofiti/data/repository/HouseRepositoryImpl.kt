package iz.housing.haofiti.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import iz.housing.haofiti.data.database.HouseInfo
import iz.housing.haofiti.data.model.HouseDataState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class HouseRepositoryImpl : HouseRepository {
    override fun fetchHouses(): Flow<HouseDataState> = callbackFlow {
        val databaseReference = FirebaseDatabase.getInstance().getReference("properties")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val houseList = mutableListOf<HouseInfo>()
                for (dataSnap in snapshot.children) {
                    val houseItem = dataSnap.getValue(HouseInfo::class.java)
                    if (houseItem != null) {
                        houseList.add(houseItem)
                    }
                }
                trySend(HouseDataState.success(houseList))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(HouseDataState.Failure(error.message))
            }
        }

        databaseReference.addListenerForSingleValueEvent(listener)

        awaitClose { databaseReference.removeEventListener(listener) }
    }
}
