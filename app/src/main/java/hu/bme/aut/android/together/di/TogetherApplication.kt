package hu.bme.aut.android.together.di

import co.zsmb.rainbowcake.dagger.RainbowCakeApplication

@Suppress("unused")
class TogetherApplication : RainbowCakeApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }
}
