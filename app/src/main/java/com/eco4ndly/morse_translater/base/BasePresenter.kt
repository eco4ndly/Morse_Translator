package com.eco4ndly.morse_translater.base

/**
 * A Sayan Porya code on 28/04/20
 */
interface BasePresenter<T: BaseView> {

    /**
     * When view is ready, add it to the presenter using this method
     *
     */
    fun subscribeView(view: T)

    /**
     * When view instance is not available anymore, destroyed most probably. Call this method to
     * remove the previously injected view to the presenter
     */
    fun unSubscribeView()
}