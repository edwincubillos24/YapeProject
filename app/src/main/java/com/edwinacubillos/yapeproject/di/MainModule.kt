package com.edwinacubillos.yapeproject.di

import com.edwinacubillos.yapeproject.data.repository.RecipesRepository
import com.edwinacubillos.yapeproject.data.repository.impl.RecipesRepositoryImpl
import com.edwinacubillos.yapeproject.data.source.RemoteRecipesDataSource
import com.edwinacubillos.yapeproject.framework.remote.api.APIService
import com.edwinacubillos.yapeproject.framework.remote.mappers.RemoteRecipeMapper
import com.edwinacubillos.yapeproject.usecases.recipes.GetRecipesUseCase
import com.edwinacubillos.yapeproject.R
import com.edwinacubillos.yapeproject.YapeProject
import com.edwinacubillos.yapeproject.data.server.RetrofitBuild
import com.edwinacubillos.yapeproject.data.server.WrappedResponseHandler
import com.edwinacubillos.yapeproject.data.server.source.RemoteRecipesImplDataSource
import com.edwinacubillos.yapeproject.ui.list.ListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

fun YapeProject.initDI() {
    try {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@initDI)
            modules(listOf(appModule, dataModule, scopesModule))
        }
    } catch (e: Exception) {
        stopKoin()
        this.initDI()
    }
}

val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }
    single { RetrofitBuild(baseUrl = androidApplication().getString(R.string.base_url)) }
    factory { WrappedResponseHandler() }
    factory { RemoteRecipeMapper() }
    factory<RemoteRecipesDataSource> {
        RemoteRecipesImplDataSource(
            apiService = get(),
            remoteRecipeMapper = get(),
            wrappedResponseHandler = get()
        )
    }
    single { get<RetrofitBuild>().retrofit.create(APIService::class.java) }
}

val dataModule = module {
    factory<RecipesRepository> { RecipesRepositoryImpl(remoteRecipesDataSource = get()) }
}

val scopesModule = module {

    viewModel { ListViewModel(GetRecipesUseCase(recipesRepository = get())) }

}
