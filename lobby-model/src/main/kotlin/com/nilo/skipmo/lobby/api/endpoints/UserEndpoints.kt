package com.nilo.skipmo.lobby.api.endpoints

enum class UserEndpoints(path : String) {
    USERS("/"),
    DETAILS( "/{id}"),
    CREATEINVITATION( "/invitation"),
    ACCEPTINVITATION( "/invitation/{id}"),
    DECLINEINVIATION( "/invitation/{id}");

    val basePath : String = "/users"
    val uri : String = basePath + path

}