package ru.nsu.group20211.dinermobile.di.home_fragment

import dagger.BindsInstance
import dagger.Component
import ru.nsu.group20211.dinermobile.di.AppComponent
import ru.nsu.group20211.dinermobile.ui.home_fragment.HomeFragment
import javax.inject.Scope

@Scope
annotation class HomeScope

@HomeScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            @BindsInstance
            messengerFragment: HomeFragment
        ): HomeComponent
    }
}