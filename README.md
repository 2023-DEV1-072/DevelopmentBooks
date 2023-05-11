# DevelopmentBooks
Book catalogue backend using spring boot
## Issue tracking
[![GitHub](https://img.shields.io/badge/issue_tracking-github-blue?logo=github)](https://github.com/2023-DEV1-072/DevelopmentBooks/issues)
## synopsis
There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books. The available books are :
1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. Clean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively With Legacy Code (Michael C. Feathers, 2004)

One copy of the five books costs 50 EUR.
- If, however, you buy two different books from the series, you get a 5% discount on those two books.
- If you buy 3 different books, you get a 10% discount.
- With 4 different books, you get a 20% discount.
- If you go for the whole hog, and buy all 5, you get a huge 25% discount.
- Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.

## Running the application
```
You can run the application using:

mvn clean install
mvn spring-boot:run
```
## To get the available book list
```
curl --location --request GET 'http://localhost:8080/api/books'
```
## To get the final price for a  book list
```
curl --location --request POST 'http://localhost:8080/api/books/calculateBooksPrice' \
--header 'Content-Type: application/json' \
--data-raw '[ {"book":{
        "id": 0,
        "title": "Clean Code",
        "author": "Robert Martin",
        "date": "2008",
        "price": 50.0
    },
    "quantity":1}]'
```