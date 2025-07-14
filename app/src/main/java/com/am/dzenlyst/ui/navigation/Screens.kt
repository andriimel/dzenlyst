package com.am.dzenlyst.ui.navigation

sealed  class Screens(val route: String) {

    object Focus : Screens("focus")
    object Tasks : Screens("tasks")
    object Stats : Screens("stats")
    object Coach: Screens("coach")
}