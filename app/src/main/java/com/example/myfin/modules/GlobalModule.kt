package com.example.myfin.modules

import com.example.myfin.domains.global.DatabaseCallInteractor
import com.example.myfin.domains.global.DatabaseCallUseCase
import com.example.myfin.repositories.global.BaseRepository
import org.koin.dsl.module

val globalModule = module {
    factory<DatabaseCallUseCase> { (repo: BaseRepository) ->
        DatabaseCallInteractor(repo)
    }
}