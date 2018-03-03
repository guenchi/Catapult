# Catapult

***Catapult*** is a web framwork for ***Igropyr***

easily to write the router

```
(req
    ("/"                index)
    ("/index"           index)
    ("/users"           users)
    ("/notes"           notes)
    ("/blog/*/en"       blogEN)
    ("/articles/*"      article)
    ("/*"               handle404)))
```

easily to define respone

```
(define index
    (lambda (req)
        (res "hello world")))

(define index
    (lambda (req)
        (res 200 "hello world")))

(define index
    (lambda (req)
        (res "text/html" "<h1>hello world</h1>")))
        
(define index
    (lambda (req)
        (res 200 "text/html" "<h1>hello world</h1>")))
```


```
(res string)                => respone content only

(res int string)            => respone status and content

(res string string)         => respone style and content

(res int string string)     => respone status, style and content
```

***install Catapult***

Catapult dependence Igropyr, but you dont't have to install it before Catapult,

because Raven can automately install it

`$ raven install catapult`

but Igropyr dependence libuv, make sure you have installed it before all.

***use Catapult***

```
(define handle_get
    (request
        (lambda (request_header path_info query_string)
            ((router get path_info) query_string))))
```

instead of

```
(define get
    (request
        (lambda (request_header path_info query_string)
                    (response 200 "text/html" RESPONSE_STRING))))
```

and run

```
(server
    handle_get
    handle_post
    (set 
        ('staticpath "/usr/local/www/"))
    (listen 8080))
```

api: https://guenchi.gitbooks.io/catapult/
