package com.tephra.mc.pulselivetest.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tephra.mc.pulselivetest.shared.Constants
import dagger.Module
import dagger.Provides

@Module
class GsonModule {

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat(Constants.APP_DATE_FORMAT)
                .create()
    }

}
