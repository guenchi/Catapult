
![img](img/catapult.jpg)

### easily to write the router

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

### easily to define respone

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

## install with [Raven](http://ravensc.com)

`$ raven install catapult`



## Use 

```
(define res-get
        (lambda (header path query)
            ((router get path-info) header path query)))
```

instead of

```
(define get
        (lambda (header path query)
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