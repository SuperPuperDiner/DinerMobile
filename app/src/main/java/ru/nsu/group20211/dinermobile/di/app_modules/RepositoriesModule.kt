package ru.nsu.group20211.dinermobile.di.app_modules

import dagger.Module
import ru.nsu.group20211.dinermobile.di.basket_fragment.BasketRepositoryModule
import ru.nsu.group20211.dinermobile.di.home_fragment.HomeRepositoryModule

@Module(
    includes = [
        BasketRepositoryModule::class,
        HomeRepositoryModule::class
    ]
)
class RepositoriesModule