package com.katerinavp.currency.di.components

import android.content.Context
import androidx.test.espresso.idling.CountingIdlingResource
import com.katerinavp.currencies_screen_impl.di.CurrencyFragmentComponent
import com.katerinavp.currency.AppTest
import com.katerinavp.currency.di.modules.CurrencyModule
import com.katerinavp.currency.di.modules.DbModule
import com.katerinavp.currency.di.modules.NetworkModule
import com.katerinavp.currency.di.modules.SubcomponentModule
import com.katerinavp.currency.di.modules.SubcomponentModuleTest
import com.katerinavp.currency.fragments.CurrencyFragmentTest
import dagger.BindsInstance
import dagger.BindsOptionalOf
import dagger.Component
import dagger.Module
import dagger.Provides
import java.util.Optional
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, CurrencyModule::class, DbModule::class, TestIdlingResourceModule::class]
)
interface AppComponentTest : AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponentTest
    }

    fun inject(test: CurrencyFragmentTest)
}

@Module
interface TestIdlingResourceModule {

    companion object {

        @Provides
        @Singleton
        fun provideIdlingResource(): CountingIdlingResource {
            return CountingIdlingResource("test_resource")
        }
    }

    @BindsOptionalOf
    fun provideIdlingResource(): CountingIdlingResource
}


