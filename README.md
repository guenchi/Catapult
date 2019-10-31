# Catapult

***Catapult*** is a web framwork for ***Igropyr***

## [Manuel](https://guenchi.github.io/Catapult)

***easily to write the router***

```
(req
    ("/"                index)
    ("/index"           index)
    ("/users"           users)
    ("/notes"           notes)
    ("/blog/*/en"       blogEN)
    ("/articles/*"      article)
    ("/*"               handle404))
```

***easily to define respone***

```
(define index
    (lambda (header path query)
        (res "hello world")))

(define index
    (lambda (header path query)
        (res 200 "hello world")))

(define index
    (lambda (header path query)
        (res "text/html" "<h1>hello world</h1>")))
        
(define index
    (lambda (header path query)
        (res 200 "text/html" "<h1>hello world</h1>")))
```


```
(res string)                => respone content only

(res int string)            => respone status and content

(res string string)         => respone style and content

(res int string string)     => respone status, style and content
```


***[Igropyr](https://guenchi.github.io/Igropyr)*** is a async http-server for Chez Scheme

Catapult's sister framwork: ***[Ballista](https://guenchi.github.io/Ballista/)*** (Express style)


