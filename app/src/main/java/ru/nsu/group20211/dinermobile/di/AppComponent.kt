package ru.nsu.group20211.dinermobile.di

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import ru.nsu.group20211.dinermobile.di.app_modules.BottomNavigation
import ru.nsu.group20211.dinermobile.di.app_modules.GlobalNavigation
import ru.nsu.group20211.dinermobile.di.app_modules.NavigationModule
import ru.nsu.group20211.dinermobile.di.app_modules.NetworkModule
import ru.nsu.group20211.dinermobile.di.app_modules.RepositoriesModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        NavigationModule::class,
        RepositoriesModule::class
    ]
)
interface AppComponent {
    @GlobalNavigation
    fun globalNavigatorHolder(): NavigatorHolder

    @GlobalNavigation
    fun globalRouter(): Router

    @BottomNavigation
    fun bottomNavigatorHolder(): NavigatorHolder

    @BottomNavigation
    fun bottomRouter(): Router
}