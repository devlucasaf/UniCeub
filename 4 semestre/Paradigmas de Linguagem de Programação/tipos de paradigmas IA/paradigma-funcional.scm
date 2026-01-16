(define (make-reserva espaco data ini fim)
  (list (cons 'espaco espaco)
        (cons 'data data)
        (cons 'ini ini)
        (cons 'fim fim)))

(define (get k alist)
  (let ((p (assoc k alist)))
    (if p (cdr p) #f)))

(define (hm->min h m) (+ (* h 60) m))

(define (min->hmstr t)
  (let ((h (quotient t 60))
        (m (remainder t 60)))
    (string-append (if (< h 10) "0" "") (number->string h)
                   ":"
                   (if (< m 10) "0" "") (number->string m))))

(define (overlap? a1 b1 a2 b2)
  (and (< a1 b2) (> b1 a2)))

(define (conflito? reservas nova)
  (let ((e (get 'espaco nova))
        (d (get 'data nova))
        (i (get 'ini nova))
        (f (get 'fim nova)))
    (let loop ((xs reservas))
      (cond
        ((null? xs) #f)
        (else
         (let ((r (car xs)))
           (if (and (equal? (get 'espaco r) e)
                    (equal? (get 'data r) d)
                    (overlap? i f (get 'ini r) (get 'fim r)))
               #t
               (loop (cdr xs)))))))))

(define (try-add reservas nova)
  (if (conflito? reservas nova)
      (list reservas "Conflito de horario")
      (list (append reservas (list nova)) "Reserva adicionada")))

(define (render r)
  (string-append (get 'espaco r) " em " (get 'data r) " "
                 (min->hmstr (get 'ini r)) "-" (min->hmstr (get 'fim r))))

(define (map2 f xs)
  (if (null? xs) '() (cons (f (car xs)) (map2 f (cdr xs)))))

(define (lines xs)
  (let loop ((ys xs) (acc ""))
    (if (null? ys) acc
        (loop (cdr ys) (string-append acc (car ys) "\n")))))

(define reservas0 '())

(define r1 (make-reserva "Salao" "20/01/2026" (hm->min 18 0) (hm->min 22 0)))
(define r2 (make-reserva "Salao" "20/01/2026" (hm->min 21 0) (hm->min 23 0)))
(define r3 (make-reserva "Churrasqueira" "20/01/2026" (hm->min 12 0) (hm->min 16 0)))

(define res1 (try-add reservas0 r1))
(define reservas1 (car res1))

(define res2 (try-add reservas1 r2))
(define reservas2 (car res2))

(define res3 (try-add reservas2 r3))
(define reservas3 (car res3))

(display (cadr res1)) (display "\n")
(display (cadr res2)) (display "\n")
(display (cadr res3)) (display "\n\n")

(display "Reservas finais:\n")
(display (lines (map2 render reservas3)))
