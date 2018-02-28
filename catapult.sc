(library (igropyr catapult)
  (export
    get
    res
  )
  (import
    (scheme)
    (igropyr http)
  )


(define-syntax get
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
                    "text/plain"
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
                                          (cons 'content e3)))))))



)



