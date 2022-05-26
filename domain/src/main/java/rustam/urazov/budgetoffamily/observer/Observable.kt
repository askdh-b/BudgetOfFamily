package rustam.urazov.budgetoffamily.observer

interface Observable {

    fun attach(observer: Observer)

    fun detach(observer: Observer)

    fun noticePositive()

    fun noticeNegative()
}