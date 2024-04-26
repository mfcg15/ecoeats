package com.mitocode.ecoats.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mitocode.ecoats.data.database.AppDatabase
import com.mitocode.ecoats.data.database.dao.DishDao
import com.mitocode.ecoats.data.database.dao.UserDao
import com.mitocode.ecoats.data.repository.DishRepositoryImp
import com.mitocode.ecoats.data.repository.LoginRepositoryImp
import com.mitocode.ecoats.data.repository.RegisterRepositoryImp
import com.mitocode.ecoats.domain.repository.DishRepository
import com.mitocode.ecoats.domain.repository.LoginRepository
import com.mitocode.ecoats.domain.repository.RegisterRepository
import com.mitocode.ecoats.presentation.util.ConnectivityChecker
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
    fun provideLoginRepository(sharedPreferences: SharedPreferences, userDao: UserDao) : LoginRepository {
        return LoginRepositoryImp(sharedPreferences,userDao)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(sharedPreferences: SharedPreferences, userDao: UserDao) : RegisterRepository {
        return RegisterRepositoryImp(sharedPreferences,userDao)
    }

    @Provides
    @Singleton
    fun provideDishRepository(sharedPreferences: SharedPreferences, dishDao: DishDao) : DishRepository {
        return DishRepositoryImp(sharedPreferences, dishDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "dbEcoats"
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(db:AppDatabase) : UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideDishDao(db:AppDatabase) : DishDao = db.dishDao()

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) : ConnectivityChecker{
        return ConnectivityChecker(context)
    }

}