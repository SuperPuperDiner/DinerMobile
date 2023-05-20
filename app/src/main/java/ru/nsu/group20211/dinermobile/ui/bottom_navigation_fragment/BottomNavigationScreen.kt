package ru.nsu.group20211.dinermobile.ui.bottom_navigation_fragment

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nsu.group20211.dinermobile.ui.basket_fragment.BasketFragment
import ru.nsu.group20211.dinermobile.ui.home_fragment.HomeFragment

object BottomNavigationScreen {
    const val KEY_SCREEN_CHANNEL = "key_home"
    const val KEY_SCREEN_PEOPLE = "key_people"
    const val KEY_SCREEN_PROFILE = "key_profile"

    fun Home() =
        FragmentScreen(KEY_SCREEN_CHANNEL, false) { HomeFragment.newInstance() }

    fun Basket() =
        FragmentScreen(KEY_SCREEN_CHANNEL, false) { BasketFragment.newInstance(5) }
}