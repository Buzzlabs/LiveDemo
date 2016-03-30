(ns livedemo.csg
  (:require [instaparse.core :as insta]))
(comment


(defn parses [g i]
  (let [parser (insta/parser g)
        result (parser i)]
    (if (insta/failure? result)
      result
      (insta/parses parser i))))


((insta/parser "S = &'a'L1 | 'b'L2
                L1 = 'x'
                L2 = 'y'")
  "x")



((insta/parser "S = S S | L | ε
                L = L1 | L2
                ':'L1 = 'a'
                ';'L2 = L2 | L2 L2 | 'b'")
  "b:b:")


((insta/parser "S =  S | S S | L | ε
                L = L1 | L2 ':'
                L1 = 'a' ';'
                L2 = L2 | L2 L2 | 'b'")
  "b:")

((insta/parser "S =  S | S S | L | ε
                L = L1 | L2 ':'
                L1 = 'a' ';'
                L2 = L2 | L2 L2 | 'b'")
  "b:")

((insta/parser "S =  S | S S | L | ε
                L = &(':') L1 | ';'L2
                L1 = L1 | L1 L1 | 'a'
                L2 = L2 | L2 L2 | 'b'")
  ";")


(def parser (insta/parser "S = ε | abc | aTBc
                          T = abC | aTBC
                          CB = CX
                          CX = BX
                          BX = BC
                          bB = bb
                          Cc = cc"))

(def parser
  (insta/parser
    "S = 'ab' ('a' | 'b')+"))

(parser "aba")
(parser "abb")

(def parser
  (insta/parser
    "S = &'ab' ('a' | 'b')+"))

(parser "aba")
(parser "abb")


(def parser (insta/parser "S = &(A 'c') 'a'+ B
                           A = 'a' A? 'b'
                           <B> = 'b' B? 'c'"))

(parser "aabbcc")

(def parser (insta/parser "S = &'a' | A
                           A = &'a' 'b'"
                          ))


(parser "abb")

)


