(ns livedemo.anagrams
  (:require [clojure.string :as s]))

#_(def palavras (s/split-lines (slurp "/tmp/Lista-de-Palavras.txt")) )

(def amostras [["NAMORO" "ROMANO"]
               ["ALISADO" "ASILADO" "ISOLADA"]
               ["BANANA" "ROMA" "LUIS"]] )

(def v (flatten amostras))

(defn chave [s]
   (s/join (sort s)) )


(defn ana [vv]
  (->> (group-by chave v)
       (filter #(-> % second count (> 1)))
       (map second)
       ))

(ana v)


































(defn anagramas [palavras]
  (->> palavras
       (group-by #(-> % sort s/join))
       (map val)
       (filter #(-> % count (>  1)))))


(anagramas (flatten amostras))


