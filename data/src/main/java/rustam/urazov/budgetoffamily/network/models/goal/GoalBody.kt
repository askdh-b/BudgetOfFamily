package rustam.urazov.budgetoffamily.network.models.goal

import com.google.gson.annotations.SerializedName

data class GoalBody(
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("user_id") val userId: Int,
)