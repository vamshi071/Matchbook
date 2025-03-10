package uk.ac.tees.mad.matchbook.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.matchbook.data.local.LeaguesDao
import uk.ac.tees.mad.matchbook.data.local.MatchBookDatabase
import uk.ac.tees.mad.matchbook.data.remote.SportsApiService
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSportApiService(retrofit: Retrofit):SportsApiService{
        return retrofit.create(SportsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):MatchBookDatabase{
        return MatchBookDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideLeaguesDao(database: MatchBookDatabase):LeaguesDao{
        return database.leaguesDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        sportsApiService: SportsApiService,
        leaguesDao: LeaguesDao
    ):Repository{
        return RepositoryImpl(sportsApiService, leaguesDao)
    }
}