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
    list-parser
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
        ((_) #''())
        ((_ (e1 e2)) #'(list (cons e1 e2)))
        ((_ (e1 e2)(e3 e4) ...) #'(list (cons e1 e2)(cons e3 e4) ...)))))


(define handle_res
    (lambda (x)
        (let ((status (ref x 'status))
                (type (ref x 'type))
                (content (ref x 'content)))
            (response
                (if (integer? status)
                    status
                    200)
                (if (null? type)
                    "text/html"
                    type)
                (if (null? content)
                    ""
                    content)))))

(define-syntax res
  (lambda (x)
      (syntax-case x ()
          ((_) #'(handle_res '()))
          ((_ e1) #'(handle_res (list (cons 'content e1))))
          ((_ e1 e2) #'(handle_res (list (cons (cond 
                                                  ((integer? e1) 'status)
                                                  ((string? e1) 'type)
                                                  (else '())) 
                                              e1)
                                          (cons 'content e2))))
          ((_ e1 e2 e3) #'(handle_res (list (cons (if (integer? e1)
                                                      'status
                                                      '())
                                                  e1)
                                          (cons (if (string? e2)
                                                      'type
                                                      '())
                                                  e2)
                                          (cons 'content e3))))
          ((_ e1 e2 e3 e4) #'(handle_res (list (cons (if (integer? e1)
                                                      'status
                                                      '())
                                                  e1)
                                          (cons (if (string? e2)
                                                      'type
                                                      '())
                                                  e2)
                                          (cons 'content (list e3 e4))))))))

(define handle_send
    (lambda (x)
        (let ((type (ref x 'type))
              (file (ref x 'file)))
            (sendfile
                (if (null? type)
                    ""
                    type)
                (if (null? file)
                    ""
                    file)))))

(define-syntax send
  (lambda (x)
      (syntax-case x ()
          ((_) #'(handle_send '()))
          ((_ e1) #'(handle_send (list (cons 'file e1))))
          ((_ e1 e2) #'(handle_send (list (cons 'type e1)
                                          (cons 'file e2)))))))


(define router
    (lambda (router path_info)
        (let ((x (match router path_info)))
            (if (null? x)
                handle404
                x))))


(define handle404
    (lambda (x . y)
      (errorpage 404 "<center><h2>& Catapult</h2></center>")))
 
 
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
 
 
  (define split
        (lambda (s c)
            (let loop ((s s))
                (define str-index
                    (lambda (s c)
                        (let ((n (string-length s)))
                            (let loop ((i 0))
                                (cond 
                                    ((>= i n) #f)
                                        ((char=? (string-ref s i) c) i)
                                        (else (loop (+ i 1))))))))
                (if (string=? s "")
                    '()
                    (let ((i (str-index s c)))
                        (if i 
                            (cons (substring s 0 i) (loop (substring s (+ i 1) (string-length s))))
                            (list s)))))))
    
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


    (define list-parser
        (lambda (str)
            (let loop ((str str)(x "{"))
                (if (null? (cdr str))
                    (string-append x "\"" (caar str) "\":\"" 
                        (if (list? (cdar str))
                            (loop (cdar str) "{")
                            (cdr (car str))) "\"}")
                    (loop (cdr str) (string-append x "\"" (caar str) "\":\"" 
                        (if (list? (cdar str))
                            (loop (cdar str) "{")
                            (cdar str)) "\"," ))))))
    

)





