package ru.kurgin.sop.ui.bottom_navigation_screen

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.R

class BottomNavigationNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager
) : AppNavigator(activity, containerId, fragmentManager) {

    override fun applyCommand(command: Command) {
        if (command is FindAndReplace) {
            val oldFragment = fragmentManager.fragments.find { it.isVisible }
            val newFragment = fragmentManager.findFragmentByTag(command.newScreen.screenKey)
            if (newFragment === oldFragment) return

            val transaction = fragmentManager.beginTransaction()
            if (newFragment == null) {
                transaction.add(
                    containerId,
                    command.newScreen.createFragment(fragmentFactory),
                    command.newScreen.screenKey
                )
            } else {
                transaction.setMaxLifecycle(newFragment, Lifecycle.State.RESUMED)
            }
            if (oldFragment != null) {
                transaction.setMaxLifecycle(oldFragment, Lifecycle.State.CREATED)
            }
            transaction.setCustomAnimations(
                R.anim.m3_side_sheet_slide_in,
                R.anim.m3_side_sheet_slide_out
            )
            transaction.commitNow()
        } else {
            super.applyCommand(command)
        }
    }
}