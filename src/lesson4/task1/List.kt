@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.IllegalArgumentException
import kotlin.math.min
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val factor = mutableListOf<Int>()
    var dividend = n
    var denominator = 2
    while (dividend > 1) {
        if (dividend % denominator == 0) {
            dividend /= denominator
            factor.add(denominator)
        } else denominator++
    }
    return factor
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val number = mutableListOf<Int>()
    var start = n
    if (n == 0) return listOf(0)
    while (start >= 1) {
        val remainder = start % base
        number.add(remainder)
        start /= base
    }
    return number.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = convert(n, base).map {
    if (it >= 10) {
        'a' + (it - 10)
    } else it
}.joinToString(separator = "")


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val topnumber = mutableListOf<String>()
    val top = n / 1000
    val bot = n % 1000
    val botnumber = mutableListOf<String>()
    val combo = listOf("", "один", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val teen = listOf(
        "десять",
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать"
    )
    if (top > 99) {
        when (top) {
            in 500..999 -> topnumber.add(combo[top / 100] + "сот")
            in 300..499 -> topnumber.add(combo[top / 100] + "ста")
            in 200..299 -> topnumber.add(combo[top / 100] + "сти")
            in 100..199 -> topnumber.add("сто")
        }
    }
    if (bot > 99) {
        when (bot) {
            in 500..999 -> botnumber.add(combo[bot / 100] + "сот")
            in 300..499 -> botnumber.add(combo[bot / 100] + "ста")
            in 200..299 -> botnumber.add(combo[bot / 100] + "сти")
            in 100..199 -> botnumber.add("сто")
        }
    }
    if (top >= 10) {
        when (top % 100) {
            in 10..19 -> topnumber.add(teen[top % 10])
            in 20..29 -> topnumber.add("двадцать")
            in 30..39 -> topnumber.add("тридцать")
            in 40..49 -> topnumber.add("сорок")
            in 90..99 -> topnumber.add("девяносто")
            in 50..89 -> topnumber.add(combo[top / 10 % 10] + "десят")
        }
    }
    if (bot >= 10) {
        when (bot % 100) {
            in 10..19 -> botnumber.add(teen[bot % 10])
            in 20..29 -> botnumber.add("двадцать")
            in 30..39 -> botnumber.add(combo[bot / 10 % 10] + "дцать")
            in 40..49 -> botnumber.add("сорок")
            in 90..99 -> botnumber.add("девяносто")
            in 50..89 -> botnumber.add(combo[bot / 10 % 10] + "десят")
        }
    }
    if (top >= 1) {
        when {
            top % 100 in 11..19 -> topnumber.add("тысяч")
            top % 10 == 1 -> topnumber.add("одна тысяча")
            top % 10 in 2..4 -> topnumber.add(combo[top % 10] + " тысячи")
            top % 10 == 0 -> topnumber.add("тысяч")
            else -> topnumber.add(combo[top % 10] + " тысяч")
        }
    }
    if (bot % 10 != 0) {
        when {
            (bot % 10 == 2) && (bot % 100 != 12) -> botnumber.add("два")
            bot % 100 !in 11..19 -> botnumber.add(combo[bot % 10])
        }
    }
    val result = topnumber + botnumber
    return result.joinToString(separator = " ")
}

/*

fun th(
    places: MutableList<MutableList<Boolean>>,
    requests: Map<String, Pair<Int, Int>>
): MutableMap<String, List<Int>> {
    val result = mutableMapOf<String, List<Int>>()
    for ((key, value) in requests) {
        val row = value.first
        var seats = value.second
        if (places[row].filter { !it }.size < seats) throw IllegalArgumentException()
        val choose = mutableListOf<Int>()
        for (s in places[row].indices) {
            if (!places[row][s]) {
                choose.add(s)
                places[row][s] = true
                seats -= 1
                if (seats == 0)
                    break
            }
        }
        result[key] = choose
    }
    println(places)
    return result


}

fun an(movers: List<String>, pets: List<String>, limit: Int): Set<String> {
    val result = mutableSetOf<String>()
    var sum = limit
    loop@ for (move in movers) {
        if (!Regex("""[a-zA-z]+: [а-я]+ - \d+(, [а-яё]+ - \d+)*""").matches(move)) throw IllegalArgumentException()
        val x = move.split(": ")
        val name = x[0]
        val y = x[1].split(", ")
        val petcost = mutableMapOf<String, Int>()
        for (i in y) {
            val petInfo = i.split(" - ")
            petcost[petInfo[0]] = petInfo[1].toInt()
        }
        for (pet in pets) {
            if (!petcost.contains(pet)) continue@loop
            sum -= petcost[pet]!!
        }
        if (sum >= 0) {
            result.add((name))
        }
    }
    return result
}


fun nl2(taxes: String, money: Int): Int {
    if (!taxes.matches(Regex("""(\d+ у.е. = \d+%; )*else = \d+%"""))) throw IllegalArgumentException()
    val res = taxes.split("; ")
    val mon = mutableListOf<Int>()
    var result = 0
    var howmuch = money
    val percent = mutableListOf<Int>()
    for (i in res) {
        val sep = i.split(" = ")
        if (sep[0] == "else") {
            mon.add(money)
            percent.add(sep[1].replace("%", "").toInt())
        } else {
            mon.add(sep[0].split(" ")[0].toInt())
            percent.add(sep[1].replace("%", "").toInt())
        }
    }
    // result = (percent[0] / 100.0 * min(mon[0], howmuch)).toInt()
    howmuch -= min(mon[0], howmuch)
    for (k in 1 until mon.size - 1) {
        if (howmuch == 0) break
        result += min(mon[k] - mon[k - 1], howmuch) * percent[k] / 100
        howmuch -= min(mon[k] - mon[k - 1], howmuch)
    }
    */
/*   for (k in mon.indices) {
           result += (mon[k + 1] - mon[k]) * percent[k] / 100
           howmuch -= (mon[k + 1] - mon[k])
           if (howmuch == mon[0]) break
       }*//*


    return result
}

fun nl(table: Map<String, Int>, taxes: String): Map<String, Int> {


    val result = mutableMapOf<String, Int>()
    val x = taxes.split("\n")
    for (i in x) {
        if (!i.matches(Regex("""[а-яА-я ]+[а-яА-я ]+- [а-яА-я ]+- \d+"""))) throw IllegalArgumentException()
        val spli = i.split(" - ")
        val name = spli[0]
        val typ = spli[1]
        val sum = spli.last().toInt()
        for ((key, values) in table) {
            if (typ.contains(key)) {
                result[name] = sum * values / 100
                break
            } else result[name] = sum * 13 / 100
        }

    }
    val y = result.toList().sortedBy { (_, value) -> value }.toMap()
    return y
}


fun zapr(carPetrols: Map<String, String>, gasStations: String): Map<String, String> {
    val x = gasStations.split("\n")
    val result = mutableMapOf<String, String>()
    val para = mutableMapOf<String, MutableMap<String, Double>>()
    for (i in x) {
        val petrol = mutableMapOf<String, Double>()
        val spl = i.split(": ")
        val name = spl[0]
        val y = spl[1].split("; ")
        for (k in y) {
            val z = k.split(" - ")
            val typ = z[0]
            val cost = z[1].removeSuffix(";").toDouble()
            petrol[typ] = cost

        }
        para[name] = petrol
    }
    for ((key, value) in carPetrols) {
        var max = 1000.00
        for ((name, all) in para) {
            if (value in all.keys && all[value]!! < max) {
                result[key] = name
                max = all[value]!!
            }
        }


    }
    return result

}*/
//Зрители в зале
/**
 * На вход подаётся изменяемый список places, содержащий информацию
 * о состоянии мест в зале в следующем виде: i-ый элемент списка
 * описывает состояние мест в i-ом ряду в зале.
 * Состояние мест в ряду также представлено списком, содержащим true,
 * если место занято, и false если свободно.
 *
 * Например:
 * [[true,false,false,false,true,false],[true,false,true,false]]
 *
 * Также, на вход подаётся ассоциативный массив requests, содержащий
 * информацию о запросах на места. Ключ это идентификатор зрителя,
 * а значение это пара из номера ряда и количества заказанных мест.
 *
 * Например:
 * {"Вася" = (0, 2), "Петя" = (1, 1)}
 * В примере Вася хочет 2 места в ряду 0, а Петя одно место в ряду 1.
 *
 * Необходимо каждому зрителю найти необходимое количество мест в зале
 * и зарезервировать их. Места могут располагаться НЕ рядом.
 * Требуется вернуть для каждого зрителя список зарезервированных
 * для него мест, а также внести изменения в переданный
 * на вход список, содержащий информацию о состоянии мест в зале.
 *
 * Если какому-либо из зрителей невозможно выделить необходимое
 * количество мест требуется выбросить IllegalStateException.
 *
 * Для данных из примера результат работы может быть следующим:
 * Зарезервированные места:
 * {"Вася" = [1, 5], "Петя" = [3]}
 * Изменённый список с информацией о местах:
 * [[true,true,false,false,true,true],[true,false,true,true]]
 *
 * Имя функции и тип результата функции предложить самостоятельно;
 * в задании указан тип Collection<Any>, то есть коллекция объектов
 * произвольного типа, можно (и нужно) изменить как вид коллекции,
 * так и тип её элементов.
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 *
 * fun myFun(places: MutableList<MutableList<Boolean>>,
 *    requests: Map<String, Pair<Int, Int>>): Collection<Any> = TODO()
 */
val places =
    mutableListOf(mutableListOf(true, false, false, false, true, false), mutableListOf(true, false, true, false))
val requests = mapOf("Вася" to Pair(0, 2), "Петя" to Pair(1, -1))

fun main() {
    //println(places[0].filter { !it }.size)
    //println(switcher(mutableListOf(true, false, false, false, true, false), 2))
    println(requests)
    println(places)
    println(theater(places, requests))
    println(places)
}

/*fun switcher(row: MutableList<Boolean>, n: Int): MutableList<Boolean> {
    var c = 0
    for (i in row.indices) {
        if (c > n) break
        if (!row[i]) row[i] = true
        c++
    }
    return row
}*/

fun theater(
    places: MutableList<MutableList<Boolean>>,
    requests: Map<String, Pair<Int, Int>>
): MutableMap<String, List<Int>> {

    val ansMap = mutableMapOf<String, List<Int>>()
    for ((key, value) in requests) {
        val row = value.first
        val seats = value.second
        //if (seats < 0 || places[row].filter { !it }.size < seats) throw IllegalStateException()
        if (places[row].filter { !it }.size < seats) throw IllegalStateException()
        val preservedSeats = mutableListOf<Int>()
        //Заносим в список и
        // заменяем false N раз в places[row] на true
        var c = 0
        for (i in places[row].indices) {
            if (c >= seats) break // алтренатива if (c == seats) break
            if (!places[row][i]) {
                preservedSeats.add(i) // заносим в список
                places[row][i] = true
                c++// меняем значение
            }

        }
        ansMap[key] = preservedSeats
        // В случае того, что Банда Мышей зарезервирует не один ряд, а два? инпут вида "X to Pair(i,n), X to Pair(j,m)?
        // Тогда
    }
    return ansMap
}