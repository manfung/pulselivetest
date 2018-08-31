package com.tephra.mc.latestnews.di.components

import com.tephra.mc.pulselivetest.PulseLiveTestApp
import com.tephra.mc.pulselivetest.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent {
    fun inject(app: PulseLiveTestApp)
}