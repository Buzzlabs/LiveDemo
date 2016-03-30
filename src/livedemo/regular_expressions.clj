(ns livedemo.regular-expressions)



(comment "Computação"
         "https://www.youtube.com/watch?v=Pt6GBVIifZA"

         "s termina em 0?"
         "file://hello.clj representa um programa clojure?"
         "file://hello.clj representa um programa cl`ojure que nunca retorna?")


(comment "Linguagens regulares"
         "Linguagens livre de contexto"
         "Linguagens sensíveis ao contexto"
         "Linguagens Recursivamente enumeráveis"
         )


(comment "

Alfabeto: 0 1

Estado:
 - Representa uma memória sobre o problema que está sendo resolvido.
 - Tudo que o que pode ser decidido baseado numa quantidade
    finita de informação.

Função de transição:
 - Diz para qual estado ir.


Uma string contém um número par de zeros?

Dois estados: E e O. E é um estado final.

Funções de transição:
E(0) = O
E(1) = E

O(0) = E
O(1) = O

E também é o estado inicial.
")

(def inputs ["" "0" "1" "01" "10" "00" "11" "001"
             "0010" "0011" "110001"  "11000"])



(def even-zeros-states {:E true
                        :O false})

(def even-zeros-transitions-map {:E {\0 :O \1 :E}
                                 :O {\0 :E \1 :O}})

(defn even-zeros-transitions [state input]
  (get-in even-zeros-transitions-map [state input]))

(even-zeros-transitions :E \0)
(even-zeros-transitions :E \1)
(even-zeros-transitions :O \0)
(even-zeros-transitions :O \1)

(reduce (fn [acc i] (* acc i)) 1 [1 2 3 4])
(reduce (fn [acc i] (+ acc i)) 0 [1 2 3 4])

(reduce * 1 [1 2 3 4])

(defn even-zeros? [s]
  (even-zeros-states (reduce even-zeros-transitions :E s)))

(map (juxt identity even-zeros?) inputs)

(defn even-zeros-re? [s]
  (boolean (re-find #"^1*(01*01*)*$" s)))


(map (juxt identity even-zeros-re?) inputs)









(def even-zeros-and-ones-states {:EE true
                                 :EO false
                                 :OE false
                                 :OO false})

(def even-zeros-and-ones-transitions-map
  {:EE {\0 :OE \1 :EO}
   :OE {\0 :EE \1 :OO}
   :EO {\0 :OO \1 :EE}
   :OO {\0 :EO \1 :OE}})

(defn even-zeros-and-ones-transitions [state input]
  (get-in even-zeros-and-ones-transitions-map [state input]))

(defn even-zeros-and-ones? [s]
  (even-zeros-and-ones-states (reduce even-zeros-and-ones-transitions :EE s)))

(map #(vector % (even-zeros-and-ones? %)) inputs)













(def ends-with-2-zeros-states {:NO_ZERO false
                               :FIRST_ZERO false
                               :SECOND_ZERO true})

(def ends-with-2-zeros-transitions-map
  {:NO_ZERO {\0 :FIRST_ZERO \1 :NO_ZERO}
   :FIRST_ZERO {\0 :SECOND_ZERO \1 :NO_ZERO}
   :SECOND_ZERO {\0 :SECOND_ZERO \1 :NO_ZERO}})

(defn ends-with-2-zeros-transitions [state input]
  (get-in ends-with-2-zeros-transitions-map [state input]))

(defn ends-with-2-zeros? [s]
  (ends-with-2-zeros-states (reduce ends-with-2-zeros-transitions :NO_ZERO s)))

(map
  #(vector
    %
    (ends-with-2-zeros? %))
  inputs)


















(def not-ends-with-2-zeros-states {:NO_ZERO true
                                   :FIRST_ZERO true
                                   :SECOND_ZERO false})


(defn not-ends-with-2-zeros? [s]
  (not-ends-with-2-zeros-states
    (reduce ends-with-2-zeros-transitions :NO_ZERO s)))


(map #(vector % (not-ends-with-2-zeros? %)) inputs)



(defn transition-map [s]
  (->> (concat s [:eof])
       (partition 2 1)
       (reduce
         (fn [{id :id :as acc}  [k v]]
           (assoc acc :id (inc id) id
                      {v (if-not (= :eof v)
                           (str v "-" (inc id) ))}))
         {:id 0})))


(reduce
  (transition-map "ab") 0 "ab+c")
