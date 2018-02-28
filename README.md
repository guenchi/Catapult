# Catapult
a Web framework for Igropyr


***Catapult*** is a web framwork for Igropyr

with it

easyly to write the router

```
(define router
    (get
        ("/"        index)
        ("/index"   index)
        ("/users"   users)
        ("/notes"   notes)))
```

easyly to define respone

(define index
    (lambda (req)
        (res
            ('status    200)
            ('type      "text/html")
            ('content   req))))

(define index
    (lambda (req)
        (res
            ('content   "Hello world"))))

(define index
    (lambda (req)
        (res 200 "text/html" "<h1>hello world</h1>")))

(define index
    (lambda (req)
        (res 200 "hello world")))

(define index
    (lambda (req)
        (res "text/html" "<h1>hello world</h1>")))


