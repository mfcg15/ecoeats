package com.mitocode.ecoats.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mitocode.ecoats.data.repository.DishRepositoryImp
import com.mitocode.ecoats.data.repository.LoginRepositoryImp
import com.mitocode.ecoats.data.repository.RegisterRepositoryImp
import com.mitocode.ecoats.domain.repository.DishRepository
import com.mitocode.ecoats.domain.repository.LoginRepository
import com.mitocode.ecoats.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharePreferences(@ApplicationContext context: Context) : SharedPreferences {

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "PREFERENCES_TOKEN",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

    @Provides
    @Singleton
    fun provideLoginRepository(sharedPreferences: SharedPreferences) : LoginRepository {
        return LoginRepositoryImp(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(sharedPreferences: SharedPreferences) : RegisterRepository {
        return RegisterRepositoryImp(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideDishRepository(sharedPreferences: SharedPreferences) : DishRepository {
        return DishRepositoryImp(sharedPreferences)
    }

}