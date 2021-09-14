package com.gomaa.marvelapp.base.domain.usecase

interface BaseUseCase<I, O> {
    suspend fun execute(input: I): O
}