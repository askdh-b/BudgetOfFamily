package rustam.urazov.budgetoffamily

import org.junit.Assert.*
import org.junit.Test
import rustam.urazov.budgetoffamily.models.IncomeData
import rustam.urazov.budgetoffamily.models.IncomesSourceData
import rustam.urazov.budgetoffamily.models.SpendingData
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.usecases.GetBalanceUseCase
import rustam.urazov.budgetoffamily.usecases.income.GetIncomesSumUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.GetIncomesSourcesSumUseCase
import rustam.urazov.budgetoffamily.usecases.spending.GetSpendingsSumUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.GetSpendingsSourceSumUseCase
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun getIncomesSum() {
        // объявляем массив
        val incomes = mutableListOf<IncomeData>()
        // создание объектов
        val incomeData1 = IncomeData(userId = 1, firstName = "", name = "", sum = 1001.0f, creationDate = "")
        val incomeData2 = IncomeData(userId = 1, firstName = "", name = "", sum = 1002.0f, creationDate = "")
        // заполнение массива
        incomes.addAll(listOf(incomeData1, incomeData2))
        // вычисление результата
        val getIncomesSumUseCase = GetIncomesSumUseCase()
        val result = getIncomesSumUseCase.execute(incomes)
        // сранивание ожидаемого и реального результат
        assertEquals(2003.0f, result)
    }
    @Test
    fun getSpendingsSum() {
        // объявляем массив
        val spendings = mutableListOf<SpendingData>()
        // создание объектов
        val spendingData1 = SpendingData(userId = 1, firstName = "", name = "", sum = 2001.0f, creationDate = "")
        val spendingData2 = SpendingData(userId = 1, firstName = "", name = "", sum = 1882.1f, creationDate = "")
        // заполнение массива
        spendings.addAll(listOf(spendingData1, spendingData2))
        // вычисление результата
        val getSpendingsSumUseCase = GetSpendingsSumUseCase()
        val result = getSpendingsSumUseCase.execute(spendings)
        // сранивание ожидаемого и реального результат
        assertEquals(3883.1f, result)
    }
    @Test
    fun getBalance() {
        // создание объектов
        val incomes = 2003.0f
        val spendings = 3883.1f
        // вычисление результата
        val getBalanceUseCase = GetBalanceUseCase()
        val result = getBalanceUseCase.execute(incomes, spendings)
        // сранивание ожидаемого и реального результат
        assertEquals(-1880.1001f, result)
    }
    @Test
    fun getIncomesSourcesSum() {
        // объявляем массив
        val incomesSources = mutableListOf<IncomesSourceData>()
        // создание объектов
        val incomesSourceData1 = IncomesSourceData(id = 1, userId = 1, name = "", sum = 1001.0f, monthDay = 11)
        val incomesSourceData2 = IncomesSourceData(id = 2, userId = 1, name = "", sum = 1002.0f, monthDay = 11)
        // заполнение массива
        incomesSources.addAll(listOf(incomesSourceData1, incomesSourceData2))
        // вычисление результата
        val getIncomesSourcesSumUseCase = GetIncomesSourcesSumUseCase()
        val result = getIncomesSourcesSumUseCase.execute(incomesSources)
        // сранивание ожидаемого и реального результат
        assertEquals(2003.0f, result)
    }
    @Test
    fun getSpendingsSourcesSum() {
        // объявляем массив
        val spendingsSources = mutableListOf<SpendingsSourceData>()
        // создание объектов
        val spendingsSourceData1 = SpendingsSourceData(id = 1, userId = 1, name = "", sum = 1901.0f, monthDay = 11)
        val spendingsSourceData2 = SpendingsSourceData(id = 2, userId = 1, name = "", sum = 1002.0f, monthDay = 11)
        // заполнение массива
        spendingsSources.addAll(listOf(spendingsSourceData1, spendingsSourceData2))
        // вычисление результата
        val getSpendingsSourceSumUseCase = GetSpendingsSourceSumUseCase()
        val result = getSpendingsSourceSumUseCase.execute(spendingsSources)
        // сранивание ожидаемого и реального результат
        assertEquals(2903.0f, result)
    }
}