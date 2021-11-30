package com.android.wordtranslator.domain.scheduler

object SchedulerFactory {
    fun create(): Schedulers = DefaultSchedulers()
}