package ng.max.binger.mvp

open class BasePresenter<T: Mvp.View>: Mvp.Presenter<T> {

    var view: T? = null

    override fun detachView() {
        this.view = null
    }

    override fun attachView(view: T) {
        this.view = view
    }

}