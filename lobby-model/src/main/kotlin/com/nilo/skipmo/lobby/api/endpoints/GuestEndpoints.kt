package com.nilo.skipmo.lobby.api.endpoints

enum class GuestEndpoints(path: String) {
    LOGIN("/logIn"),
    CREATEUSER("/createUser"),
    USERS("/users");

    val basePath = "/guests"
    val uri = basePath + path

}