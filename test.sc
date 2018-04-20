(import 
    (igropyr igropyr)
    (ballista ballista))

(define header "HTTP/1.1\r\n Host: 127.0.0.1:8081\r\nUpgrade-Insecure-Requests: 1\r\nAccept: text/html,application/xhtml+xml,application/xml,q=0.9,*/*,q=0.8\r\nUser-Agent: Mozilla/5.0 (Macintosh, Intel Mac OS X 10_13_3) AppleWebKit/604.5.6 (KHTML, like Gecko) Version/11.0.3 Safari/604.5.6\r\nAccept-Language: zh-cn\r\nAccept-Encoding: gzip, deflate\r\nConnection: keep-alive\r\n")

(define query "user=igropyr&psw=catapult")

(define routerlist
    (req 
        ("/abc" 'abc)
        ("/*/abc" 'abc*)
        ("/*" 'x)))


(newline)
(display "Start test...")
(newline)
(newline)


(display "test procedure req...")
(display
    (if 
        (and
            (equal? (ref routerlist "/abc") 'abc)
            (equal? (ref routerlist "/*/abc") 'abc*)
            (equal? (ref routerlist "/*") 'x))
        "               ok"
        "               error"))
(newline)

(display "test procedure host?...")
(display
    (if 
        (equal? (host? header) "127.0.0.1:8081")
        "             ok"
        "             error"))
(newline)



(display "test procedure user-agent?...")
(display
    (if 
        (equal? (user-agent? header) "Mozilla/5.0 (Macintosh, Intel Mac OS X 10_13_3) AppleWebKit/604.5.6 (KHTML, like Gecko) Version/11.0.3 Safari/604.5.6")
        "       ok"
        "       error"))
(newline)


(display "test procedure accept-language?...")
(display
    (if 
        (equal? (accept-language? header) "zh-cn")
        "  ok"
        "  error"))
(newline)


(display "test procedure accept-encoding?...")
(display
    (if 
        (equal? (accept-encoding? header) "gzip, deflate")
        "  ok"
        "  error"))
(newline)


(display "test procedure cookie?...")
(display
    (if 
        (equal? (cookie? header) "")
        "           ok"
        "           error"))
(newline)


(display "test procedure connection?...")
(display
    (if 
        (equal? (connection? header) "keep-alive")
        "       ok"
        "       error"))
(newline)


(display "test procedure query-parser...")
(display
    (if 
        (and
            (equal? (ref (query-parser query #\= #\&) "user") "igropyr")
            (equal? (ref (query-parser query #\= #\&) "psw") "catapult"))
        "      ok"
        "      error"))
(newline)
(newline)


(display "Test complished!")
(newline)
(newline)

