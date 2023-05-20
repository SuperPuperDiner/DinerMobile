package ru.kurgin.sop.ui.bottom_navigation_screen

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen


class FindAndReplace(val newScreen: FragmentScreen) : Command

class BottomNavigationRouter : Router() {
    fun findAndReplace(screen: FragmentScreen) {
        this.executeCommands(FindAndReplace(screen))
    }
}