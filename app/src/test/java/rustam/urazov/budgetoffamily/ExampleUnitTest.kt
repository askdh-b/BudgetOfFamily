package rustam.urazov.budgetoffamily

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd")
        val localDate: LocalDate = LocalDate.now()
        val dtf2 = DateTimeFormatter.ofPattern("HH:mm")
        val localTime: LocalTime = LocalTime.now()
        assertEquals("13", "${dtf.format(localDate)} ${dtf2.format(localTime)}")
    }
}