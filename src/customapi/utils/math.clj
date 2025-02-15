(ns customapi.utils.math)

(defn factorial [n]
  (if (= n 0)
    1
    (* n (factorial (- n 1)))))

(defn fibonacci [n]
  (if (<= n 1)
    n
    (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))

(defn tail-factorial [n]
  (letfn [(fact-helper [n acc]
            (if (<= n 1)
              acc
              (recur (dec n) (* acc n))))]
    (fact-helper n 1)))

(defn iterative-fibonacci [n]
  (loop [a 0, b 1, i n]
    (if (zero? i)
      a
      (recur b (+ a b) (dec i)))))
