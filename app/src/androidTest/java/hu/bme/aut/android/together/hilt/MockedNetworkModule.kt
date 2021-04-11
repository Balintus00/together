package hu.bme.aut.android.together.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.android.together.di.NetworkModule
import hu.bme.aut.android.together.mock.FakeNetworkDataSource
import hu.bme.aut.android.together.network.NetworkDataSource
import javax.inject.Singleton

@Suppress("unused")
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
abstract class MockedNetworkModule {

    @Singleton
    @Binds
    abstract fun bindFakeNetworkDataSource(networkManager:FakeNetworkDataSource) : NetworkDataSource

}