package lesson4.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class Tests {
    @Test
    @Tag("Example")
    fun sqRoots() {
        assertEquals(listOf<Double>(), sqRoots(-1.0))
        assertArrayEquals(listOf(0.0).toDoubleArray(), sqRoots(0.0).toDoubleArray(), 1e-5)
        assertArrayEquals(listOf(-5.0, 5.0).toDoubleArray(), sqRoots(25.0).toDoubleArray(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun biRoots() {
        assertEquals(listOf<Double>(), biRoots(0.0, 0.0, 1.0))
        assertEquals(listOf<Double>(), biRoots(0.0, 1.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, 2.0).toDoubleArray(),
            biRoots(0.0, 1.0, -4.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, -2.0, 4.0))
        assertArrayEquals(
            listOf(-1.0, 1.0).toDoubleArray(),
            biRoots(1.0, -2.0, 1.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, 3.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, -1.0, 1.0, 2.0).toDoubleArray(),
            biRoots(1.0, -5.0, 4.0).sorted().toDoubleArray(),
            1e-5
        )
    }

    @Test
    @Tag("Example")
    fun negativeList() {
        assertEquals(listOf<Int>(), negativeList(listOf(1, 2, 3)))
        assertEquals(listOf(-1, -5), negativeList(listOf(-1, 2, 4, -5)))
    }

    @Test
    @Tag("Example")
    fun invertPositives() {
        val list1 = mutableListOf(1, 2, 3)
        invertPositives(list1)
        assertEquals(listOf(-1, -2, -3), list1)
        val list2 = mutableListOf(-1, 2, 4, -5)
        invertPositives(list2)
        assertEquals(listOf(-1, -2, -4, -5), list2)
    }

    @Test
    @Tag("Example")
    fun squares() {
        assertEquals(listOf(0), squares(listOf(0)))
        assertEquals(listOf(1, 4, 9), squares(listOf(1, 2, -3)))
    }

    @Test
    @Tag("Example")
    fun squaresVararg() {
        assertArrayEquals(arrayOf(0), squares(0))
        assertArrayEquals(arrayOf(1, 4, 9), squares(1, 2, -3))
    }

    @Test
    @Tag("Example")
    fun isPalindrome() {
        assertFalse(isPalindrome("Барабан"))
        assertTrue(isPalindrome("А роза упала на лапу Азора"))
        assertTrue(isPalindrome("Шалаш"))
    }

    @Test
    @Tag("Example")
    fun buildSumExample() {
        assertEquals("42 = 42", buildSumExample(listOf(42)))
        assertEquals("3 + 6 + 5 + 4 + 9 = 27", buildSumExample(listOf(3, 6, 5, 4, 9)))
    }

    @Test
    @Tag("2")
    fun abs() {
        assertEquals(0.0, abs(listOf()), 1e-5)
        assertEquals(3.0, abs(listOf(3.0)), 1e-5)
        assertEquals(5.0, abs(listOf(3.0, -4.0)), 1e-5)
        assertEquals(8.774964, abs(listOf(4.0, -5.0, 6.0)), 1e-5)
    }

    @Test
    @Tag("2")
    fun mean() {
        assertEquals(0.0, mean(listOf()), 1e-5)
        assertEquals(1.0, mean(listOf(1.0)), 1e-5)
        assertEquals(2.0, mean(listOf(3.0, 1.0, 2.0)), 1e-5)
        assertEquals(3.0, mean(listOf(0.0, 2.0, 7.0, 8.0, -2.0)), 1e-5)
    }

    @Test
    @Tag("3")
    fun center() {
        assertEquals(listOf<Double>(), center(mutableListOf()))
        assertArrayEquals(
            listOf(0.0).toDoubleArray(),
            center(mutableListOf(3.14)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(1.0, -1.0, 0.0).toDoubleArray(),
            center(mutableListOf(3.0, 1.0, 2.0)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(-3.0, -1.0, 4.0, 5.0, -5.0).toDoubleArray(),
            center(mutableListOf(0.0, 2.0, 7.0, 8.0, -2.0)).toDoubleArray(),
            1e-5
        )
        val toMutate = mutableListOf(-3.0, -1.0, 4.0, 5.0, -5.0)
        assertTrue(toMutate === center(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("3")
    fun times() {
        assertEquals(0, times(listOf(), listOf()))
        assertEquals(-5, times(listOf(1, -4), listOf(3, 2)))
        assertEquals(-19, times(listOf(-1, 2, -3), listOf(3, -2, 4)))
    }

    @Test
    @Tag("3")
    fun polynom() {
        assertEquals(0, polynom(listOf(), 1000))
        assertEquals(42, polynom(listOf(42), -1000))
        assertEquals(13, polynom(listOf(3, 2), 5))
        assertEquals(0, polynom(listOf(2, -3, 1), 1))
        assertEquals(45, polynom(listOf(-7, 6, 4, -4, 1), -2))
    }

    @Test
    @Tag("3")
    fun accumulate() {
        assertEquals(listOf<Double>(), accumulate(arrayListOf()))
        assertArrayEquals(
            listOf(3).toIntArray(),
            accumulate(arrayListOf(3)).toIntArray()
        )
        assertArrayEquals(
            listOf(1, 3, 6, 10).toIntArray(),
            accumulate(arrayListOf(1, 2, 3, 4)).toIntArray()
        )
        val toMutate = mutableListOf(-3, -1, 4, 5, -5)
        assertTrue(toMutate === accumulate(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("3")
    fun factorize() {
        assertEquals(listOf(2), factorize(2))
        assertEquals(listOf(3, 5, 5), factorize(75))
        assertEquals(listOf(2, 3, 3, 19), factorize(342))
    }

    @Test
    @Tag("4")
    fun factorizeToString() {
        assertEquals("2", factorizeToString(2))
        assertEquals("3*5*5", factorizeToString(75))
        assertEquals("2*3*3*19", factorizeToString(342))
        assertEquals("7*7*31*31*151*151", factorizeToString(1073676289))
        assertEquals("1073676287", factorizeToString(1073676287))
        assertEquals(Int.MAX_VALUE.toString(), factorizeToString(Int.MAX_VALUE))
    }

    @Test
    @Tag("3")
    fun convert() {
        assertEquals(listOf(1), convert(1, 2))
        assertEquals(listOf(1, 2, 1, 0), convert(100, 4))
        assertEquals(listOf(1, 3, 12), convert(250, 14))
        assertEquals(listOf(2, 14, 12), convert(1000, 19))
    }

    @Test
    @Tag("4")
    fun convertToString() {
        assertEquals("1", convertToString(1, 2))
        assertEquals("1210", convertToString(100, 4))
        assertEquals("13c", convertToString(250, 14))
        assertEquals("2ec", convertToString(1000, 19))
        assertEquals("z", convertToString(35, 36))
        assertEquals("a02220281", convertToString(Int.MAX_VALUE, 11))
    }

    @Test
    @Tag("3")
    fun decimal() {
        assertEquals(1, decimal(listOf(1), 2))
        assertEquals(100, decimal(listOf(1, 2, 1, 0), 4))
        assertEquals(250, decimal(listOf(1, 3, 12), 14))
        assertEquals(1000, decimal(listOf(2, 14, 12), 19))
    }

    @Test
    @Tag("4")
    fun decimalFromString() {
        assertEquals(1, decimalFromString("1", 2))
        assertEquals(100, decimalFromString("1210", 4))
        assertEquals(250, decimalFromString("13c", 14))
        assertEquals(1000, decimalFromString("2ec", 19))
        assertEquals(35, decimalFromString("z", 36))
        assertEquals(Int.MAX_VALUE, decimalFromString("a02220281", 11))
    }

    @Test
    @Tag("5")
    fun roman() {
        assertEquals("I", roman(1))
        assertEquals("MMM", roman(3000))
        assertEquals("MCMLXXVIII", roman(1978))
        assertEquals("DCXCIV", roman(694))
        assertEquals("XLIX", roman(49))
    }

    @Test
    @Tag("7")
    fun russian() {
        assertEquals("триста семьдесят пять", russian(375))
        assertEquals("двадцать две тысячи девятьсот шестьдесят четыре", russian(22964))
        assertEquals("сто девятнадцать тысяч пятьсот восемь", russian(119508))
        assertEquals("две тысячи три", russian(2003))
        assertEquals("двести тысяч два", russian(200002))
        assertEquals("девятьсот тысяч", russian(900000))
        assertEquals("двенадцать", russian(12))
    }
/*
    @Test
    fun th() {
        val one = mutableListOf<Boolean>(true, false, false, false, true, false)
        val two = mutableListOf<Boolean>(true, false, true, false)
        val request1 = mapOf("Вася" to Pair(0, 2), "Петя" to Pair(1, 1))
        assertEquals(mapOf("Вася" to listOf(1, 2), "Петя" to listOf(1)),
            lesson5.task1.th(mutableListOf(one, two), request1)
        )

    }

    @Test
    fun an() {
        val movers = listOf<String>(
            "SuperCats: кот - 100000",
            "FastAndCheap: кот - 25000, собака - 30000, шиншилла - 5000",
            "Lux: кот - 1000000, собака - 1000000, крыса - 1000000, корова - 1000000, бегемот - 1000000"
        )
        assertEquals(setOf("Lux", "FastAndCheap"), lesson5.task1.an(movers, listOf("кот", "собака"), 20000000))
    }


    @Test
    fun nl2() {
        *//*val taxes = "20000 у.е. = 0%; 40000 у.е. = 5%; 60000 у.е. = 10%; else = 25%"
        assertEquals(13000, nl2(taxes, 100000))*//*
        val taxe = "20000 у.е. = 0%; 40000 у.е. = 5%; 60000 у.е. = 10%; else = 25%"
        assertEquals(2000, lesson5.task1.nl2(taxe, 50000))
    }

    @Test
    fun nl() {
        val taxes =
            "ООО Горняк - Горнодобывающая промышленость - 100000\nВбербанк - Банковские операции - 190000\nПолитек Ведра - Образование - 9000000"
        assertEquals(
            mapOf("ООО Горняк" to 12000, "Вбербанк" to 17100, "Политек Ведра" to 1170000),
            lesson5.task1.nl(
                mapOf("Производство напитков" to 4, "Горнодобывающая промышленость" to 12, "Банковские операции" to 9),
                taxes
            )
        )
        assertThrows(IllegalArgumentException::class.java) {
            lesson5.task1.nl(
                mapOf("Производство напитков" to 4, "Горнодобывающая промышленость" to 12, "Банковские операции" to 9),
                "GGGGGG"
            )
        }

    }

    @Test
    fun zapr() {
        val car = mapOf(
            "Lada Vesta" to "бензин 98",
            "Lada Niva" to "дизель",
            "BMW M5" to "бензин 95",
            "Копейка" to "бензин 88",
            "Трактор" to "солярка"
        )
        val gas =
            "Лукойл: бензин 95 - 44.66; дизель - 60.76; солярка - 10;\nГазпром: бензин 98 - 50.00; бензин 88 - 34.30;\nShell: бензин 66 - 23.00; дизель - 55.50;"
        assertEquals(
            mapOf(
                "Lada Vesta" to "Газпром",
                "Lada Niva" to "Shell",
                "BMW M5" to "Лукойл",
                "Копейка" to "Газпром",
                "Трактор" to "Лукойл"
            ), lesson5.task1.zapr(car, gas)
        )
    }*/

    @Test

    fun theater() {

        val places1 =
            mutableListOf(
                mutableListOf(true, false, false, false, true, false),
                mutableListOf(true, false, true, false)
            )
        val requests1 = mapOf("Вася" to Pair(0, 2), "Петя" to Pair(1, 1))
        assertEquals(mapOf("Вася" to listOf(1, 2), "Петя" to listOf(1)), theater(places1, requests1))

        val places2 =
            mutableListOf(
                mutableListOf(true, true, true, true, false, false),
                mutableListOf(true, true, true, false)
            )
        val requests2 = mapOf("Вася" to Pair(0, 2), "Петя" to Pair(1, 1))
        assertEquals(mapOf("Вася" to listOf(4, 5), "Петя" to listOf(3)), theater(places2, requests2))

        val places3 =
            mutableListOf(
                mutableListOf(true, false, true, false, false, false),
                mutableListOf(true, true, true, false),
                mutableListOf(false, false, false, false)
            )
        val requests3 = mapOf("Вася" to Pair(0, 2), "Петя" to Pair(1, 1), "Банда Мышей" to Pair(2, 10))
        //assertEquals(mapOf("Вася" to listOf(4, 5), "Петя" to listOf(3)), theater(places3, requests3))
        assertThrows(IllegalStateException::class.java) {
            theater(places3, requests3)
        }
        val places4 =
            mutableListOf(
                mutableListOf(false, false, false, false, false, false, false),
                mutableListOf(false, false, false, false, false, false, false),
                mutableListOf(false, false, false, false, false, false, false)
            )
        val requests4 = mapOf("Вася" to Pair(0, 6), "Петя" to Pair(0, 1), "Банда Мышей" to Pair(2, 7))
        assertEquals(
            mapOf(
                "Вася" to listOf(0, 1, 2, 3, 4, 5),
                "Петя" to listOf(6),
                "Банда Мышей" to listOf(0, 1, 2, 3, 4, 5, 6)
            ),
            theater(places4, requests4)
        )


    }
}