# Archiver
Чтобы запустить программу необходимо собрать jar файл, для этого сделать mvn clean install в папке с проектом

Запуск программы для архивации:
java -jar Archiver-1.0-SNAPSHOT.jar *название файлов или директорий через пробел* > *название инфо-файла*

Запуск программы для деархивации:
cat *название инфо-файла* | java -jar Archiver-1.0-SNAPSHOT.jar
