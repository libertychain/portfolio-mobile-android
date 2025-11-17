package com.example.meucartaodevisitas.di

import android.content.Context
import androidx.room.Room
import com.example.meucartaodevisitas.data.local.ProjectDatabase
import com.example.meucartaodevisitas.data.local.dao.ProjectDao
import com.example.meucartaodevisitas.data.remote.GitHubApiService
import com.example.meucartaodevisitas.data.repository.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideProjectDatabase(
        @ApplicationContext context: Context
    ): ProjectDatabase {
        return Room.databaseBuilder(
            context,
            ProjectDatabase::class.java,
            ProjectDatabase.DATABASE_NAME
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideProjectDao(database: ProjectDatabase): ProjectDao {
        return database.projectDao()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GitHubApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideProjectRepository(
        projectDao: ProjectDao,
        apiService: GitHubApiService
    ): ProjectRepository {
        return ProjectRepository(projectDao, apiService)
    }
}