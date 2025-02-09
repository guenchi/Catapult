# Catapult

***Catapult*** is a web framework for ***Igropyr***

## [Manual](https://guenchi.github.io/Catapult)

***easy to write the router***

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

***easy to define responses***

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
(res string)                => response content only

(res int string)            => response status and content

(res string string)         => response style and content

(res int string string)     => response status, style and content
```


***[Igropyr](https://guenchi.github.io/Igropyr)*** is an async http-server for Chez Scheme

Catapult's sister framework: ***[Ballista](https://guenchi.github.io/Ballista/)*** (Express-style)


