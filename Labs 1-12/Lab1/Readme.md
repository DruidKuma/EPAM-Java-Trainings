(Актуально для Mac OSX 10.9.2)

I.
1. Найти где установлена Java
При помощи впомогательной комманды в терминале:
/usr/libexec/java_home
найдем значение переменной окружения JAVA_HOME и таким образом найдем, где установлена Java:
/Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home

2. Узнать версию Java
Полную информацию о версии Java узнаем с помощью комманды:
java -version
На машине установлена следующая версия Java:
java version "1.8.0"
Java(TM) SE Runtime Environment (build 1.8.0-b132)
Java HotSpot(TM) 64-Bit Server VM (build 25.0-b70, mixed mode)
Также есть возможность узнать отдельно и в более сокращенной форме версию компилятора и дизассемблера Java с помощью аналогичных комманд:
javac -version
javac 1.8.0
или
javap -version
1.8.0

3. Узнать значение переменной окружения JAVA_HOME 
Ответ дан в пункте 1 
 

II.
1. Попытаться откомпилировать примеры из src.zip
a) Test1.java
Test1.java:6: error: class TestAnother1 is public, should be declared in a file named TestAnother1.java
public class TestAnother1 {
       ^
1 error
б) Test2.java
Test2.java:1: error: class Testing2 is public, should be declared in a file named Testing2.java
public class Testing2 {
       ^
1 error
в) Test3.java
Компиляция проходит успешно. Но при запуске откомпилированной программы:
Error: Main method not found in class Test3, please define the main method as:
   public static void main(String[] args)
or a JavaFX application class must extend javafx.application.Application
г) Test4.java
Компиляция проходит успешно. Но при запуске откомпилированной программы:
Error: Main method is not static in class Test4, please define the main method as:
   public static void main(String[] args)


2. Сказать, что в них неправильно и добиться их работоспособности
а) Test1.java
По законам Java, мы можем иметь в одном java файле только один публичный класс, и его название должно быть идентично названию самого файла. (Но мы можем создавать сколько угодно вложенных публичных классов с произвольными названиями). 
Такое ограничение облегчает компилятору нахождение описаний классов, а стало быть, ускоряет его работу. Также, как считается, это улучшает читаемость кода.
Исправим ошибку, либо просто закомментировав не понравившийся компилятору класс, либо, если все же класс нужно оставить, убрав из его объявления public. Программа скомпилируется и запустится в обоих случаях, выведя в консоль:
What's wrong with this program?

б) Test2.java
Компилятору не понравилось название нашего файла. Причина тому уже описана выше. Для того, чтобы исправить файл, изменим названием публичного класса на Test2. Программа скомпилируется и выведет в консоль:
What's wrong with this program?

в) Test3.java
Ошибка кроется в объявлении главного метода, как нам прямолинейно заявила JVM. Если присмотреться, то увидим, что метод main в нашем файле в качестве аргумента принимает строку args, в то время, как должен принимать массив строк. По сути, этот массив – это дополнительные аргументы из коммандной строки, и их может быть сколько угодно.
Чтобы исправить ошибку, в объявлении метода main изменим аргумент (String args) на (String[] args). Программа скомпилируется и выведет уже знакомую фразу:
What's wrong with this program?

г) Test4.java
Неправильно объявлен главный метод – упущен ключевой модификатор static. Static позволяет вызывать метод main из класса без необходимости создавать экземпляр класса. Для main() это важно, поскольку этот метод вызывается JVM еще перед созданием любых объектов.
Исправить просто – добавим недостающий модификатор static в его законное место. Компилируем и запускаем:
What's wrong with this program?

III.
Создать иерархию папок (…)
Вся указаная иерархия папок создается при помощи базовых консольных комманд:
cd <имя папки> - перейти в папку, находящуюся в данной директории (если не указано имя папки, команда переводит в домашнюю директорию
ls – показать все файлы, находящиеся в данной директории
mkdir <имя папки> - создать в данной директории папку с указанным именем
…
3. Откомпилировать PersonRunner из командной строки так, чтобы файлы *.class писались в папку build/classes
Нам нужно указать компилятору папку для сохранения *.class файлов (для этого используем опцию  “-d <directory>”, и также нужно указать путь, где компилятор найдет наш класс Person. Для этого используем опцию “-cp <path>”. Конечное указание для компилятора (находясь в папке Lab_1):  
javac -d build/classes -cp src PersonRunner.java
4. Запустить программу.
Для корректного запуска программы указываем JVM путь, где лежат наши *.class файлы:
java -cp build/classes PersonRunner

5. Откомпилировать еще раз с выводом информации о компиляции, посмотреть полученный байт-код
Для того, чтобы просмотреть информацию о компиляции (точнее, о том, что делает компилятор), добавим к команде компилятора опцию “-verbose”:
javac -verbose -d build/classes -cp src PersonRunner.java
Затем, чтобы посмотреть полученный байт-код, запустим дизассемблер со следующими опциями:
javap -c -cp build/classes PersonRunner
Опция “-с” очень важна, так как именно она дизассемблирует код класса. Без этой опции javap просто покажет нам список методов и переменных класса.

6. Запустить программу с информацией о том, какие грузятся классы
Чтобы вывести информацию о том, какие грузятся классы, используем опцию “-verbose:class” при запуске программы:
java -verbose:class -cp build/classes PersonRunner

7. Сгенерировать JavaDoc в папку docs
Сгенерируем JavaDoc для нашего класса Person. Для этого в консоли пропишем:
javadoc -d docs -author -version src/Person.java
“-d docs” - указываем конечную папку, куда будут помещаться все файлы документации
“-author -version” - опции для отображения в документации информации об авторе и версии (без этих опций отображаться не будут)