(import (igropyr http)
        (catapult catapult))

(printf "server is start, listen on port..~a\n" 8080)

(define index
    (lambda (query)
        (res "this is index")))

(define user
    (lambda (query)
        (res 200 "text/html" query)))

(define page
    (lambda (query)
        (res 200 "PAGE")))

(define note
    (lambda (query)
        (res "text/html" "<h1>NOTE</h1>")))

(define erro
    (lambda (query)
        (res 200 "text/html" "<h1>erro</h1>")))


(define get
    (req
        ("/"        index)
        ("/index"   index)
        ("/user"    user)
        ("/page"    page)
        ("/*/note"  note)
        ("/erro/*"  erro)))


(define post
    (req
        ("/"        index)
        ("/index"   index)
        ("/user"    user)
        ("/page"    page)
        ("/*/note"  note)
        ("/erro/*"  erro)))


(define res-get
        (lambda (request-header path-info query-string)
            ((router get path-info) query-string)))

(define req-post
    (request
        (lambda (request-header path-info payload)
            ((router post path-info) payload))))


(server 
    (request res-get)    
    req-post      
    (set 
        ('staticpath "/users/local/www/"))
    (listen 8080))





