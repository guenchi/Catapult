(import (igropyr http)
        (catapult catapult))
      

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


(define handle_get
    (callback
        (lambda (request_header path_info query_string)
            ((use get path_info) query_string))))

(define handle_post
    (callback
        (lambda (request_header path_info payload)
            ((use post path_info) payload))))


(server 
    handle_get     
    handle_post      
    (set 
        ('staticpath "/users/local/www/"))
    (listen 8080))

(printf "server is start, listen on port..~a\n" 8080)



(define index
    (lambda (req)
        (res "this is index")))

(define user
    (lambda (req)
        (res 200 "text/html" req)))

(define pages
    (lambda (req)
        (res 200 "PAGE")))

(define note
    (lambda (req)
        (res "text/html" "<h1>NOTE</h1>")))

(define erro
    (lambda (req)
        (res 200 "text/html" "<h1>erro</h1>")))

