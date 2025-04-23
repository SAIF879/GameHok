package com.example.gamehok.data.model

import com.google.gson.annotations.SerializedName


data class TournamentData (

    @SerializedName("id"                  ) var id                  : Int?              = null,
    @SerializedName("name"                ) var name                : String?           = null,
    @SerializedName("thumbnailPath"       ) var thumbnailPath       : String?           = null,
    @SerializedName("entryFees"           ) var entryFees           : Int?              = null,
    @SerializedName("prizeCoins"          ) var prizeCoins          : String?           = null,
    @SerializedName("tournamentStartTime" ) var tournamentStartTime : Int?              = null,
    @SerializedName("registrationEndTime" ) var registrationEndTime : Int?              = null,
    @SerializedName("organizerDetails"    ) var organizerDetails    : OrganizerDetails? = OrganizerDetails(),
    @SerializedName("status"              ) var status              : String?           = null,
    @SerializedName("gameName"            ) var gameName            : String?           = null,
    @SerializedName("teamSize"            ) var teamSize            : String?           = null,
    @SerializedName("registeredCount"     ) var registeredCount     : Int?              = null,
    @SerializedName("totalCount"          ) var totalCount          : Int?              = null

)



data class OrganizerDetails (

    @SerializedName("id"               ) var id               : String? = null,
    @SerializedName("name"             ) var name             : String? = null,
    @SerializedName("profileImagePath" ) var profileImagePath : String? = null

)