package ru.nsu.group20211.dinermobile.di.bottom_navigation_fragment

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.kurgin.sop.ui.bottom_navigation_screen.BottomNavigationNavigator
import ru.kurgin.sop.ui.bottom_navigation_screen.BottomNavigationRouter
import ru.nsu.group20211.dinermobile.R
import ru.nsu.group20211.dinermobile.di.app_modules.BottomNavigation
import ru.nsu.group20211.dinermobile.ui.bottom_navigation_fragment.BottomNavigationFragment
import javax.inject.Scope

@Scope
annotation class BottomNavScope

@BottomNavScope
@Component(modules = [BottomNavigationModule::class])
interface BottomNavigationComponent {

    fun inject(fragment: BottomNavigationFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            bottomNavFragment: BottomNavigationFragment
        ): BottomNavigationComponent
    }
}

@Module
class BottomNavigationModule {
    private val globalCicerone: Cicerone<Router> = Cicerone.create(BottomNavigationRouter())

    @Provides
    @BottomNavScope
    @BottomNavigation
    fun provideRouter(): BottomNavigationRouter {
        return globalCicerone.router as BottomNavigationRouter
    }

    @Provides
    @BottomNavScope
    @BottomNavigation
    fun provideNavigatorHolder(): NavigatorHolder {
        return globalCicerone.getNavigatorHolder()
    }

    @Provides
    @BottomNavScope
    @BottomNavigation
    fun provideNavigator(bottomNavFragment: BottomNavigationFragment): Navigator {
        return BottomNavigationNavigator(
            bottomNavFragment.requireActivity(),
            R.id.bottom_fragment_container,
            bottomNavFragment.childFragmentManager
        )
    }
}