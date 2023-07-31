package com.katerinavp.currency.data.db.model

import android.content.Context
import androidx.room.Room
import com.katerinavp.currency.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton


/**
 * Инициализация базы данных
 *
 */
@Suppress("unused")
@Module
class DbModule {

    /**
     * Базовый провайдер базы данных
     *
     * @param context Контекст
     * @return База данных
     */
    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        const val DB_NAME = "currency.db"
    }
}
