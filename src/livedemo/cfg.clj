(ns livedemo.cfg
  (:require [instaparse.core :as insta]
            [clojure.pprint :refer [pprint]]
            [clojure.core.match :refer [match]]
            [clojure.edn :as edn]))



(defn parses [g i]
  (let [parser (insta/parser g)
        result (parser i)]
    (if (insta/failure? result)
      result
      (insta/parses parser i))))


(def g "S = '0' S '1' | ε")

(parses g "")
(parses g "01")
(parses g "00111")

(comment
  "0     -> '0'       S       '1'
             ^

   0     -> '0'  '0'  S  '1'  '1'
                  ^

   1     -> '0'  '0'  ε  '1'  '1'
                          ^

   1     -> '0'  '0'  ε  '1'  '1'
                               ^

   1     -> "
  )

(parses g "00011")



(parses g "0011")
(parses g "00111")
(parses g "000111")



(def g "S = '0' S '1' | S S | ε")

(parses g "")
(parses g "01")
(parses g "010011")
(parses g "001011")
(parses g "00100110110011")

(parses g "01000111010110")
(parses g "01000111010110")



(def parser2a (insta/parser "S = '(' S ')' | S S | ε"))

(parser2a "")
(parser2a "()")
(parser2a "()(())")
(parser2a "(()())")
(parser2a "(()(())())(())")


; SGML (Standard Generalized Markup Language)

(def simple-sgml-grammar
  "S = '<' name '>' S '</' name '>' | S S | ε
  name  = #'\\w+'")

(def simple-sgml-parser (insta/parser simple-sgml-grammar))

(simple-sgml-parser "<a></a>")




(def parser4 (insta/parser "S = S op S | int
                            op = '*' | '+'
                            int = #'\\d+'"))
(parser4 "1+2")
(parser4 "1+2*3")

(pprint (insta/parses parser4 "1+2*3"))



(def parser5 (insta/parser "S =  <'('> S ')' op <'('> S ')'| int
                            op = '+' | '*'
                            int = #'\\d+'"))
(parser5 "1+2")
(parser5 "42")
(parser5 "(42)")
(parser5 "1+(2*3)")

(pprint (insta/parses parser5 "1+2*3"))


(def transformer
  (partial insta/transform
           {:int #(vector :int (clojure.edn/read-string %) )
            :op #(if (= % "+") + *)}))



(defn eval-S [tree]
  (println (count tree) " - " tree "\n")
  (match [tree]
         #_[[:S i]] #_(do
                    #_(println "i = " i)
                      i)
         [[:S [:S S]]] (eval-S S)
         [[:S [:int i]]] i

         [[:S S1 op S2]] (do
                           #_(println "S1 op S2 = " S1 op S2)
                           (op (eval-S S1) (eval-S S2)))

         ))

(->> (parser4 "1+2*3+40")
     transformer
     eval-S)


(comment
  (->> (parser5 "(1+3)*2")
       transformer
       eval-S)


(->> (parser5 "1+(3*2)")
     transformer
     eval-S)
  )

(->> (insta/parses parser4 "1+2*3")
     (map (comp eval-S transformer)))






; contect meaning

(def same-numbers-of-zeros-and-ones-grammar
  "S = '0' A | '1' B | ε
  A = '1' S | '0' A A
  B = '0' S | '1' B B"
  )

((insta/parser same-numbers-of-zeros-and-ones-grammar) "011001")

(insta/parses (insta/parser same-numbers-of-zeros-and-ones-grammar) "011001")





(def same-numbers-of-zeros-and-ones-grammar-recursive
  "S = S A B | ε
   A = '0' S '1' | ε
   B = '1' S '0' | ε"
  )


((insta/parser same-numbers-of-zeros-and-ones-grammar-recursive) "00011011")

(insta/parses (insta/parser same-numbers-of-zeros-and-ones-grammar-recursive) "011001")



