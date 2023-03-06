package com.buruto.ahr.di

import com.buruto.ahr.repository.HeroRepository
import com.buruto.ahr.repository.HeroRepositoryImpl
import org.koin.dsl.module

val module = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}