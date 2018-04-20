# Ballista

***Ballista*** is a web framwork for ***Igropyr***


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
    (lambda (query)
        (res "hello world")))

(define index
    (lambda (query)
        (res 200 "hello world")))

(define index
    (lambda (query)
        (res "text/html" "<h1>hello world</h1>")))
        
(define index
    (lambda (query)
        (res 200 "text/html" "<h1>hello world</h1>")))
```


```
(res string)                => respone content only

(res int string)            => respone status and content

(res string string)         => respone style and content

(res int string string)     => respone status, style and content
```

***install Ballista***

`$ raven install catapult`



***use Ballista***

```
(define res-get
        (lambda (request-header path-info query-string)
            ((router get path-info) query-string)))
```

instead of

```
(define get
        (lambda (request-header path-info query-string)
                    (response 200 "text/html" RESPONSE_STRING)))
```

and run

```
(server
    (request res-get)
    (request res-post)
    (set 
        ('staticpath "/usr/local/www/"))
    (listen 8080))
```

api: https://guenchi.gitbooks.io/catapult/

***Igropyr*** is a async http-server for Chez Scheme

https://github.com/guenchi/Igropyr

her sister framwork: ***Trabutium*** (express style)

https://github.com/guenchi/Trabutium
