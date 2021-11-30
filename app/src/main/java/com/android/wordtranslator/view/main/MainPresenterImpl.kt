package com.android.wordtranslator.view.main

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import com.android.wordtranslator.domain.model.AppState
import com.android.wordtranslator.domain.repository.RepositoryFactory
import com.android.wordtranslator.domain.scheduler.SchedulerFactory
import com.android.wordtranslator.domain.scheduler.Schedulers
import com.android.wordtranslator.presenter.IPresenter
import com.android.wordtranslator.view.base.IView

class MainPresenterImpl<T : AppState, V : IView>(
    private val interactor: MainInteractor = MainInteractor(RepositoryFactory.create()),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val scheduler: Schedulers = SchedulerFactory.create()
) : IPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}