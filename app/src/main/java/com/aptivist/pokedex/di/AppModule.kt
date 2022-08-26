package com.aptivist.pokedex.di

import com.aptivist.pokedex.BuildConfig
import com.aptivist.pokedex.domain.IImageLoader
import com.aptivist.pokedex.domain.framework.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePicasso() : Picasso = Picasso.get()

    @Singleton
    @Provides
    fun provideImageLoader(picasso: Picasso) : IImageLoader = PicassoImageLoader(picasso)

}