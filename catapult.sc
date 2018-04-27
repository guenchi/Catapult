;  MIT License

;  Copyright guenchi (c) 2018 
         
;  Permission is hereby granted, free of charge, to any person obtaining a copy
;  of this software and associated documentation files (the "Software"), to deal
;  in the Software without restriction, including without limitation the rights
;  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;  copies of the Software, and to permit persons to whom the Software is
;  furnished to do so, subject to the following conditions:
         
;  The above copyright notice and this permission notice shall be included in all
;  copies or substantial portions of the Software.
         
;  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;  SOFTWARE.





(library (catapult catapult)
  (export
    req
    res
    send
    router
    host?
    user-agent?
    accept-language?
    accept-encoding?
    cookie?
    connection?
    query-parser
  )
  (import
    (scheme)
    (igropyr http)
    (igropyr igropyr)
  )


    (define ref*
        (lambda (str x)
            (if (null? str)
                '()
                (if (par (caar str) x)
                    (cdar str)
                    (ref* (cdr str) x)))))

    (define match
        (lambda (str x)
            (let ((y (ref str x)))
                (if (null? y)
                    (ref* str x)
                    y))))

  
    (define-syntax req
        (lambda (x)
            (syntax-case x ()
                ((_) (syntax '()))
                ((_ (e1 f1)) (syntax 
                                (list (cons e1 f1))))
                ((_ (e1 f1)(e2 f2) ...) (syntax
                                            (list (cons e1 f1)(cons e2 f2) ...))))))
        


    (define-syntax res
        (lambda (x)
                (syntax-case x ()
                    ((_ e1) (syntax 
                                (response
                                    (if (integer? e1) e1 200)
                                    "text/html" 
                                    (if (string? e1) e1 ""))))
                    ((_ e1 e2) (syntax
                                    (response 
                                        (if (integer? e1) e1 200)
                                        (if (string? e1) e1 "text/html")
                                        e2)))
                    ((_ e1 e2 e3) (syntax
                                        (response 
                                            (if (integer? e1) e1 200)
                                            (if (string? e2) e2 "text/html")
                                            e3)))
                    ((_ e1 e2 e3 e4) (syntax
                                        (response 
                                            (if (integer? e1) e1 200)
                                            (if (string? e2) e2 "text/html")
                                            (list e3 e4)))))))



    (define-syntax send
        (lambda (x)
            (syntax-case x ()
                ((_ e1) (syntax (sendfile "" e1)))
                ((_ e1 e2) (syntax (sendfile e1 e2))))))


    (define router
        (lambda (router path_info)
            (let ((x (match router path_info)))
                (if (null? x)
                    handle404
                    x))))


    (define handle403
        (lambda x
            (errorpage 403 "<center><h5>Powered by Trabutium</h5></center>")))


    (define handle404
        (lambda x
            (errorpage 404 "<center><h5>Powered by Catapult</h5></center>")))
    

    (define host?
        (lambda (x)
            (header-parser x "Host")))

    (define user-agent?
        (lambda (x)
            (header-parser x "User-Agent")))

    (define accept-language?
        (lambda (x)
            (header-parser x "Accept-Language")))

    (define accept-encoding?
        (lambda (x)
            (header-parser x "Accept-Encoding")))

    (define cookie?
        (lambda (x)
            (header-parser x "Cookie")))

    (define connection?
        (lambda (x)
            (header-parser x "Connection")))


    
    (define query-parser
        (lambda (str x y)
            (let loop ((str (split str y)))
                (define f 
                    (lambda (str)
                        (let ((str (split (car str) x)))
                            (cons (car str) (cadr str)))))
                (if (null? (cdr str))
                    (cons (f str) '())
                    (cons (f str) (loop (cdr str)))))))
    

)





