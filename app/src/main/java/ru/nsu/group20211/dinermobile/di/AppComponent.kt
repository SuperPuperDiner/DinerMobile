package ru.nsu.group20211.dinermobile.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
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

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}