package com.tephra.mc.pulselivetest.di.modules

import android.app.Application
import com.tephra.mc.latestnews.di.modules.DatabaseModule
import com.tephra.mc.latestnews.di.modules.ServiceModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class,
        GsonModule::class,
        ServiceModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        DatabaseModule::class])
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}