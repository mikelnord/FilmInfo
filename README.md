# FilmInfo
Приложение для получения информации о фильме, кратком описании и оценке.
Отображение списка фильмов по категориям.

Описание по пакетам проекта
api - вызовы API.KINOPOISK с использованием RETROFIT
Data - класс хранилища, отвечающий за запуск запросов API и кэширование ответов в памяти
класс хранилища выполнен с использованием RemoteMediator
Model — модель данных которая так же является таблицей в базе данных реализованной с помощью Room
ui - классы, связанные с отображением Activity с помощью  RecyclerView

В приложении использованны решения
RemoteMediator - использует базу данных в качестве источника достоверных данных, которые должны отображаться в пользовательском интерфейсе. 
Всякий раз, когда у нас больше нет данных в базе данных, мы запрашиваем дополнительные данные из сети, получая плавную прокрутку в RecyclerView 
Однонаправленный поток данных реализованный с помощью Flow и SharedFlow