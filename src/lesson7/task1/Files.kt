@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.math.max
import kotlin.math.min

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    File(outputName).bufferedWriter().use {
        File(inputName).forEachLine { line ->
            if (!line.startsWith("_")) {
                it.write(line)
                it.newLine()
            }
        }
    }
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var len = 0
    File(inputName).forEachLine { line ->
        if (line.trim().length > len) {
            len = line.trim().length
        }
    }
    File(inputName).forEachLine { line ->
        val k = (len - line.trim().length) / 2
        writer.write(line.trim().padStart(k + line.trim().length))
        writer.newLine()
    }
    writer.close()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var len = 0
    File(inputName).forEachLine { line ->
        if (line.replace(Regex("""\s+"""), " ").trim().length > len) {
            len = line.replace(Regex("""\s+"""), " ").trim().length
        }
    }
    File(inputName).forEachLine { line ->
        val onespace = line.replace(Regex("""\s+"""), " ").trim().length
        var needspace = len - onespace //кол-во добавляемых пробелов
        //var count = onespace / needspace
        val words = line.trim().split(Regex("""\s+""")).toMutableList() //все слова, разделение требует пробелов
        while (needspace > 0 && words.size > 1) { //добавление необходимого количества пробелов
            for (i in 0 until words.size - 1) { //после каждого слова и по кругу
                words[i] += " "
                needspace -= 1
                when (needspace) {
                    0 -> break
                }
            }
        }
        writer.write(words.joinToString(" "))
        writer.newLine()
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

/**
 * В файле с именем inputName задана таблица действительных чисел.
 * Столбцы таблицы разделены запятыми и пробелами.
 * Каждая строка содержит не более 26 значений. Пример:
 *
 * 1.5, 2.67, 3.0, 1.4
 * 5.2, 7.1, -4.8, 0.0
 * 1.4, 6.0, 2.5, -1.9  *
 * В строковом параметре range  задан диапазон из двух ячеек
 * этой таблицы, разделённых чёрточкой, например “A2-C4” или
 * “A31-B42”
 * Ячейки закодированы так: столбец задаётся заглавной буквой
 * латинского алфавита (первый столбец это буква А),
 * а строка - целым числом (первая строка это число 1).
 *
 * Необходимо посчитать среднее арифметическое значений во всех
 * ячейках заданного диапазона заданной таблицы. Диапазон задаёт
 * углы прямоугольника -- например “А2-С3” соответствует
 * ячейкам A2, A3, B2, B3, C2, C3
 *
 * “Удовлетворительно” -- все строки содержат одинаковое
 * количество чисел, заданный диапазон относится к одной строке,
 * первая ячейка в нём обязательно находится слева,
 * например, “B3-D3” (содержит B3, C3, D3)
 *
 * “Хорошо” -- диапазоны могут содержать ячейки из разных строк
 * с произвольным положением углов, например, “B1-A2”
 * соответствует ячейкам A1, A2, B1, B2
 *
 * “Отлично” -- строки могут содержать разное количество
 * чисел. Кроме того, диапазон может включать ячейки за пределами
 * входной таблицы, это не является ошибкой,
 * ячейки за пределами таблицы просто не учитываются.
 * Пример: диапазон “E1-B2” содержит B1, C1, D1, B2, C2, D2
 *
 * При нарушении форматов входных данных следует выбрасывать
 * исключение IllegalArgumentException. При невозможности
 * прочитать файл выбрасывать исключение IOException.
 *
 * Предложить самостоятельно имя функции. Кроме функции следует
 * написать тесты, подтверждающие её работоспособность.
 */
fun myFun(inputName: String, range: String): Double {
    if (!range.matches(Regex("""[A-Z]\d+-[A-Z]\d+"""))) throw IllegalArgumentException()
    val arr = mutableListOf<List<Double>>()
    try {
        File(inputName).forEachLine {
            val line = it.split(", ").map { num -> num.toDouble() }
            arr.add(line)
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    } catch (e: Exception) {
        throw IOException()
    }

    val parsedRange = range.split("-")
    val top = parsedRange[0].removeRange(0..0).toInt() - 1
    val bot = parsedRange[1].removeRange(0..0).toInt() - 1
    val left = parsedRange[0][0] - 'A'
    val right = parsedRange[1][0] - 'A'
    var sum = .0
    var count = 0
    for (i in min(top, bot)..max(top, bot)) {
        for (j in min(left, right)..max(left, right)) {
            try {
                sum += arr[i][j]
                count++
            } catch (e: IndexOutOfBoundsException) {
                continue
            }
        }
    }
    return sum / count
}

/**
 * В файле с именем inputName заданы ежедневные сведения о
 * количестве выпавших осадков (в мм) в различные месяцы года,
 * всего не более чем 31 значение в каждой строке и
 * не более 12 строк во всём файле, например:
 *
 * Март 0 1 0 3 41 2 0 0 13 16 20 8 0 4 8 1 0 0 0 7 12 0 4 9
 * Апрель 0 0 0 17 0 0 11 48 42 0 0 1 7 15 18 0 0 0 0 0 8 2 17 0
 * Май 10 15 48 21 0 0 17 22 30 0 0 13 0 0 2 5 7 0 0 0 1 10 3
 *
 * Каждая строка начинается с названия месяца, за которым
 * следует последовательность целых чисел - уровень осадков в мм
 * в различные дни этого месяца, начиная с 1-го. Порядок месяцев
 * в файле должен соответствовать реальному (следующий месяц всегда
 * ниже предыдущего).
 *
 * В строковом параметре days задан интервал дат
 * либо в формате “Апрель 9..15”  (дни в одном месяце),
 * либо в формате “Март 22..Май 8” (дни в разных месяцах).
 *
 * Необходимо рассчитать максимальный уровень осадков за один день
 * в заданном интервале дат. Например, для “Апрель 9..15” это 42,
 * для “Март 22..Май 8” это 48. Отсутствующие дни игнорировать.
 *
 * “Удовлетворительно” -- используется только первый формат для
 * параметра days - все дни в одном месяце
 *
 * “Хорошо” -- может использоваться как первый, так и второй
 * формат для параметра days, то есть, интервал может содержать
 * дни в разных месяцах
 *
 * “Отлично” -- результат функции должен содержать не только
 * максимальный уровень осадков, но и список дней,
 * в которых он был достигнут
 * (42, 9 апреля или 48, 8 апреля, 3 мая для примеров выше)
 *
 * При нарушении форматов входных данных следует выбрасывать
 * исключение IllegalArgumentException. При невозможности
 * прочитать файл выбрасывать исключение IOException.
 *
 * Предложить имя и тип результата функции. Кроме функции
 * следует написать тесты, подтверждающие её работоспособность.
 */
fun down(inputName: String, days: String): Int {
    val arr = mutableListOf<List<String>>()
    File(inputName).forEachLine {
        val line = it.split(" ")
        arr.add(line)
    }
    val spl = days.split("..")
    val nameLeft = spl[0].split(" ")[0]
    val numberLeft = spl[0].split(" ")[1].toInt()
    //val nameRight = spl[1].split(" ")[0]
    //val numberRight = spl[1].split(" ")[1]
    val numberRight = spl[1].toInt()
    var sum = mutableListOf<Int>()
    for (i in arr) {
        if (i[0] == nameLeft) {
            val x = i.subList(1, i.size)
            for (j in numberLeft - 1..numberRight)
                sum.add(x[j].toInt())
        }

    }
    return sum.max()
}

/* if regex Март - Май
for (i in arr) {
        if (i[0] == nameLeft) {
        while(i[0] != nameRight)
 */
//Задание: в файле задаётся список команд и их результаты по итогам турнира ( результат пишется через дефис, где первое значение - результат самой команды). 2 соответствует победе, 1 - ничьей и 0 - поражения.
//На удовлетворительно - вывести название команды, набравшей максимальное кол-во очков по итогам турнира, если таких команд несколько, вывести любую из них
//На хорошо - вывести отсортированный список команд. Сортировка делается по принципу : сначала сортируется по кол-ву очков, если кол-во одинаковое - отсортировать по кол-ву побед, если и кол-во побед одинаковое, отсортировать по названию команды в алфавитном порядке
//Не забываем выбрасывать исключение, если данные записаны неправильно и если ячейка таблицы (i,j) не коррелирует с ячейкой таблицы (j,i)
//

/*data class Teams(val name: String, var won: Int = 0, var draw: Int = 0)*/

/*fun dota(inputName: String): String {
    var team = mutableListOf<Teams>()
    File(inputName).forEachLine {
        val line = it.split("|")
        team.add(Teams(line[0].trim()))
        for (i in 1 until line.size) {
            when (line[i][0]) {
                '2' -> team.last().won += 1
                '1' -> team.last().draw += 1

            }
        }

    }
    return(team.maxBy { it.won * 2 + it.draw }.name)
}*/

/*data class Team(val name: String, var won: Int = 0, var draw: Int = 0)

fun dota(inputName: String) {
    val teams = mutableListOf<Team>()
    File(inputName).forEachLine {
        val line = it.split('|')
        teams.add(Team(line[0].trim()))
        for (i in 1 until line.size) {
            when (line[i][0]) {
                '2' -> teams.last().won += 1
                '1' -> teams.last().draw += 1
            }
        }
    }
    var sorted = false
    while (!sorted) {
        sorted = true
        for (i in 0 until teams.size - 1) {
            if ((teams[i].won * 2 + teams[i].draw) < (teams[i + 1].won * 2 + teams[i + 1].draw)) {
                sorted = false
                val temp = teams[i]
                teams[i] = teams[i + 1]
                teams[i + 1] = temp
            } else if ((teams[i].won * 2 + teams[i].draw) == (teams[i + 1].won * 2 + teams[i + 1].draw)) {
                if (teams[i].won < teams[i + 1].won) {
                    sorted = false
                    val temp = teams[i]
                    teams[i] = teams[i + 1]
                    teams[i + 1] = temp
                } else if (teams[i].won == teams[i + 1].won) {
                    if (teams[i].name > teams[i + 1].name) {
                        sorted = false
                        val temp = teams[i]
                        teams[i] = teams[i + 1]
                        teams[i + 1] = temp
                    }
                }
            }
        }
    }
    for ((name) in teams) {
        println(name)
    }
}*/

data class Team(val name: String, var won: Int = 0, var draw: Int = 0) : Comparable<Team> {
    override fun compareTo(other: Team): Int {
        if (this.won * 2 + this.draw > other.won * 2 + other.draw) {
            return 1
        } else if (this.won * 2 + this.draw < other.won * 2 + other.draw) {
            return -1
        } else {
            return if (this.won > other.won) {
                1
            } else if (this.won < other.won) {
                -1
            } else {
                if (this.name < other.name) {
                    1
                } else if (this.name > other.name) {
                    -1
                } else {
                    0
                }
            }
        }
    }
}

fun dota(inputName: String) {
    var teams = mutableListOf<Team>()
    File(inputName).forEachLine {
        val line = it.split('|')
        teams.add(Team(line[0].trim()))
        for (i in 1 until line.size) {
            when (line[i][0]) {
                '2' -> teams.last().won += 1
                '1' -> teams.last().draw += 1
            }
        }
    }
    teams = teams.sortedDescending().toMutableList()
    for ((name) in teams) {
        println(name)
    }
}


/*
* В файле с именем inputName задан текст, содержащий в себе элементы
* разметки adoc (см. ниже). Преобразовать текст в соответствии с разметкой
* в формат HTML и вывести его в файл с именем outputName.
* Соблюсти следующие правила:
*
* “Удовлетворительно” — обернуть весь текст тегами <html><body>...</body></html>.
* Поддержать adoc заголовки (строки, начинающиеся с =, ==, ===)
* = Заголовок 1 adoc (заменить на) <h1>Заголовок 1 adoc</h1>
* == Заголовок 2 adoc (заменить на) <h2>Заголовок 2 adoc</h2>
* === Заголовок 3 adoc (заменить на) <h3>Заголовок 3 adoc</h3>
*
* “Хорошо” — кроме этого, поддержать параграфы и переводы строк.
* Границами параграфов adoc являются заголовки (=, ==, ===) и пустые строки.
* В HTML параграфы обернуть в <p>...</p>.
* Перевод строки в adoc кодируется символом +, в HTML он заменяется тегом <br>.
*
* При нарушении формата входных данных следует выбрасывать
* исключение IllegalArgumentException. При невозможности
* прочитать файл выбрасывать исключение IOException.
*
* Кроме функции следует написать тесты, подтверждающие её работоспособность.
*/
//fun foo(inputName: String, outputName: String) {
//    val writer = File(outputName).bufferedWriter() =
//
//}

fun adoc(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.write("<html><body>")
    var hasP = false
    File(inputName).forEachLine {
        if (it.startsWith("= ")) {
            if (hasP) {
                writer.write("</p>")
                hasP = false
            }
            writer.write("\n<h1>${it.removePrefix("= ")}</h1>\n")
        } else if (it.startsWith("== ")) {
            if (hasP) {
                writer.write("</p>")
                hasP = false
            }
            writer.write("\n<h2>${it.removePrefix("== ")}</h2>\n")
        } else if (it.startsWith("=== ")) {
            if (hasP) {
                writer.write("</p>")
                hasP = false
            }
            writer.write("\n<h3>${it.removePrefix("=== ")}</h3>\n")
        } else {
            if (it.isNotEmpty()) {
                if (!hasP) {
                    writer.write("<p>")
                    hasP = true
                }
                writer.write(it.replace("+", "<br>"))
            } else {
                writer.write("</p>\n")
                hasP = false
            }
        }
    }
    if (hasP) writer.write("</p>")
    writer.write("</body></html>")
    writer.close()
}

/**
 * Робот Вася потерялся на поле. Поле задается в следующем формате:
 * xxxxxxOx
 * xxOxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxOxxxOx
 * xxRxxxxx
 * xxxxxOOx
 *
 * На поле отмечены состояния клеток, всего их может быть три: x — пустая
 * клетка, O — клетка с препятствием, R — клетка с роботом. Считайте что
 * на поле всегда ровно одна клетка занятая роботом.
 *
 * Роботу прислали список команд, которые он должен выполнить, чтобы добраться до
 * точки эвакуации. Состояние поля задается в файле с именем inputName, список
 * команд для робота приходит в списке commands. Вам необходимо вернуть состояние
 * поля после того как робот выполнит все команды. Если робот пытается выйти за
 * пределы поля или перейти на клетку с препятствием — бросить IllegalStateException.
 *
 * "Удовлетворительно" — Поле всегда имеет размер 8х8. У робота есть две команды:
 * ХОД — робот делает один шаг в том направлении, в которое он
 * сейчас смотрит. Изначально робот всегда смотрит на север (т.е. вверх).
 * На Хорошо: ПОВОРОТ — робот поворачивается на 90 градусов по часовой стрелке
 * (север -> восток -> юг -> запад -> северр).
 *
 *
 * Имя и тип результата функции предложить самостоятельно. Кроме функции,
 * следует написать тесты, подтверждающие её работоспособность
 */

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

class Robot(var x: Int, var y: Int, private var face: Direction) {
    fun move() {
        when (face) {
            Direction.NORTH -> y -= 1
            Direction.EAST -> x += 1
            Direction.SOUTH -> y += 1
            Direction.WEST -> x -= 1
        }
    }

    fun turn() {
        face = when (face) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }
}

fun vasily(inputName: String, commands: List<String>, outputName: String) {
    val obstacles = mutableListOf<Pair<Int, Int>>()
    var vasily = Robot(0, 0, Direction.NORTH)
    var width = 0
    var height = 0
    File(inputName).forEachLine {
        width = it.length
        for (i in it.indices) {
            if (it[i] == 'O') {
                obstacles.add(i to height)
            } else if (it[i] == 'R') {
                vasily = Robot(i, height, Direction.NORTH)
            } else if (it[i] != 'x') {
                throw IllegalArgumentException()
            }
        }
        height += 1
    }
    for (command in commands) {
        if (command == "ХОД") {
            vasily.move()
        } else if (command == "ПОВОРОТ") {
            vasily.turn()
        }
        if (vasily.x < 0 || vasily.y < 0 || vasily.x >= width || vasily.y >= height || (vasily.x to vasily.y) in obstacles) {
            throw IllegalStateException()
        }
    }
    File(outputName).bufferedWriter().use {
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (j to i in obstacles) {
                    it.write("O")
                } else if (i == vasily.y && j == vasily.x) {
                    it.write("R")
                } else {
                    it.write("x")
                }
            }
            it.newLine()
        }
    }
}
//Резльтаты гонок Формулы
data class Formula(val name: String, val pilots: MutableList<String>, var points: Int) : Comparable<Formula> {

    override fun compareTo(other: Formula): Int {
        return if (this.points > other.points) {
            1
        } else if (this.points < other.points) {
            -1
        } else {
            if (this.name < other.name) {
                1
            } else if (this.name > other.name) {
                -1
            } else {
                0
            }
        }

    }
}


fun rase(inputName: String) {
    var arr = mutableListOf<Formula>()
    File(inputName).forEachLine {
        if (it.contains(""":""") || it.isEmpty()) return@forEachLine
        val line = it.split(", ")
        if (line[1] in arr.map { it.name }) {
            for (i in arr) {
                if (i.name == line[1]) {
                    i.pilots.add(line[0])
                    i.points += line[2].toInt()
                    break
                }
            }
        } else arr.add(Formula(line[1], mutableListOf(line[0]), line[2].toInt()))

    }
    arr = arr.sortedDescending().toMutableList()
    for (name in arr) {
        println("${name.name}, ${name.points}")
    }

}

/*fun main() {
    rase("input/Rase.txt")
}*/
//Список действий и правка строк
fun act(inputName: String, changes: List<String>, outputName: String) {
    val arr = mutableMapOf<Int, String>()
    File(inputName).forEachLine {
        val line = it.split(" ", limit = 2)
        arr[line[0].toInt()] = line[1]
    }
    for (i in changes) {
        val line = i.split(" ", limit = 2)
        arr[line[0].toInt()] = line[1]
    }
    val y = arr.toList().sortedBy { (key, value) -> key }
    File(outputName).bufferedWriter().use {
        for (i in y) {
            it.write("${i.first} ${i.second}")
            it.newLine()
        }
    }
}
fun main() {
    act("input/act.txt", changes = listOf("2 Следующий акт", "5 Вторая глава"), outputName = "list.txt")
}