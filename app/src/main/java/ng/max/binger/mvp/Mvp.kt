package ng.max.binger.mvp

interface Mvp {

    interface View

    interface Presenter<V: Mvp.View> {
        fun attachView(view: V)
        fun detachView()
    }
}