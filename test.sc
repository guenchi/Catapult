(import (igropyr http)
        (catapult catapult))

(printf "server is start, listen on port..~a\n" 8080)

(define index
    (lambda (query)
        (res "this is index")))

(define user
    (lambda (query)
        (res 200 "text/html" (if query query "nothing"))))

(define pages
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
        ("/pages"   pages)
        ("/*/note"  note)
        ("/erro/*"  erro)))


(define post
    (req
        ("/"        index)
        ("/index"   index)
        ("/user"    user)
        ("/pages"   pages)
        ("/*/note"  note)
        ("/erro/*"  erro)))


(define req-get
    (router
        (lambda (request_header path_info query_string)
            ((use get path_info) query_string))))

(define req-post
    (router
        (lambda (request_header path_info payload)
            ((use post path_info) payload))))


(server 
    req-get     
    req-post      
    (set 
        ('staticpath "/users/local/www/"))
    (listen 8080))





