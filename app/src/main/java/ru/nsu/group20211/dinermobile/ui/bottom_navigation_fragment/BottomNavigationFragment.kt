package ru.nsu.group20211.dinermobile.ui.bottom_navigation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import ru.kurgin.sop.ui.bottom_navigation_screen.BottomNavigationRouter
import ru.nsu.group20211.dinermobile.R
import ru.nsu.group20211.dinermobile.databinding.FragmentBottomNavigationBinding
import ru.nsu.group20211.dinermobile.di.app_modules.BottomNavigation
import ru.nsu.group20211.dinermobile.di.bottom_navigation_fragment.DaggerBottomNavigationComponent
import javax.inject.Inject


class BottomNavigationFragment : Fragment() {
    private lateinit var binding: FragmentBottomNavigationBinding

    @Inject
    @BottomNavigation
    internal lateinit var localRouter: BottomNavigationRouter

    @Inject
    @BottomNavigation
    internal lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @BottomNavigation
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerBottomNavigationComponent
            .factory()
            .create(this)
            .inject(this)
        if (savedInstanceState == null)
            localRouter.newChain(BottomNavigationScreen.Home())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun setupUi() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_action_home -> {
                    localRouter.findAndReplace(
                        BottomNavigationScreen.Home()
                    )
                    true
                }

                R.id.menu_action_cart -> {
                    localRouter.findAndReplace(
                        BottomNavigationScreen.Basket()
                    )
                    true
                }

                else -> false
            }
        }
    }

    companion object {
        fun newInstance() =
            BottomNavigationFragment()
    }
}