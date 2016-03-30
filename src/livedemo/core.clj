(ns livedemo.core
  (require [livedemo.vars :refer :all]
           [clojure.string :as s]
           [clojure.edn :as edn]
           [clojure.core.match :refer [match]]
           [instaparse.core :as insta]
           [clojure.pprint :refer [pprint]]
           [clojure.walk :as walk]

           ))
(comment
(comment
  "https://github.com/Buzzlabs/LiveDemo"

  "http://www.paulgraham.com/avg.html"

  "Lisp is worth learning for the profound enlightenment
  experience you will have when you finally get it;
  that experience will make you a better programmer
  for the rest of your days, even if you never actually
  use Lisp itself a lot.

  -- Eric Raymond - How to Become a Hacker"

  "http://www.michaelnielsen.org/ddi/lisp-as-the-maxwells-equations-of-software/"
  ))


(comment
  ; (a + b) * (c + d)

  ;eax = 1
  mov eax, 1

  ;ebx = 2
  mov ebx, 2

  ; 3 = eax =  eax + ebx
  add eax, ebx

  ;ecx = 3
  mov ecx, 3

  ;edx = 4
  mov edx, 4

  ; 7 = ecx =  ecx + edx
  add ecx, edx

  ; 21 = eax = eax * ecx
  imul eax, ecx)


(comment
  int a = 1;
  int b = 2;
  int c = 3;
  int d = 4;
  int soma1 = a + b;
  int soma2 = c + d;
  int resultado = soma1 * soma2;
  )

(comment
  ; clang -Xclang -ast-dump -fsyntax-only test.cc

  kenny:tmp leandro$ clang -Xclang -ast-dump -fsyntax-only test.cc
  TranslationUnitDecl 0x1030218d0 <<invalid sloc>>
  |-TypedefDecl 0x103021e10 <<invalid sloc>> __int128_t '__int128'
  |-TypedefDecl 0x103021e70 <<invalid sloc>> __uint128_t 'unsigned __int128'
  |-TypedefDecl 0x103022230 <<invalid sloc>> __builtin_va_list '__va_list_tag [1]'
      |-VarDecl 0x103022290 <test.cc:1:1, col:9> a 'int'
  | `-IntegerLiteral 0x1030222e8 <col:9> 'int' 1
  |-VarDecl 0x103022320 <line:2:3, col:11> b 'int'
  | `-IntegerLiteral 0x103022378 <col:11> 'int' 2
  |-VarDecl 0x1030223b0 <line:3:3, col:11> c 'int'
  | `-IntegerLiteral 0x103022408 <col:11> 'int' 3
  |-VarDecl 0x103022440 <line:4:3, col:11> d 'int'
  | `-IntegerLiteral 0x103022498 <col:11> 'int' 4
  |-VarDecl 0x1030224d0 <line:5:3, col:19> soma1 'int'
  | `-BinaryOperator 0x1030225a8 <col:15, col:19> 'int' '+'
  |   |-ImplicitCastExpr 0x103022578 <col:15> 'int' <LValueToRValue>
  |   | `-DeclRefExpr 0x103022528 <col:15> 'int' lvalue Var 0x103022290 'a' 'int'
  |   `-ImplicitCastExpr 0x103022590 <col:19> 'int' <LValueToRValue>
  |     `-DeclRefExpr 0x103022550 <col:19> 'int' lvalue Var 0x103022320 'b' 'int'
  |-VarDecl 0x10306cc10 <line:6:3, col:19> soma2 'int'
  | `-BinaryOperator 0x10306cce8 <col:15, col:19> 'int' '+'
  |   |-ImplicitCastExpr 0x10306ccb8 <col:15> 'int' <LValueToRValue>
  |   | `-DeclRefExpr 0x10306cc68 <col:15> 'int' lvalue Var 0x1030223b0 'c' 'int'
  |   `-ImplicitCastExpr 0x10306ccd0 <col:19> 'int' <LValueToRValue>
  |     `-DeclRefExpr 0x10306cc90 <col:19> 'int' lvalue Var 0x103022440 'd' 'int'
  |-EmptyDecl 0x10306cd10 <col:62>
  `-VarDecl 0x10306cd40 <line:7:3, col:27> resultado 'int'
  `-BinaryOperator 0x10306ce18 <col:19, col:27> 'int' '*'
  |-ImplicitCastExpr 0x10306cde8 <col:19> 'int' <LValueToRValue>
  | `-DeclRefExpr 0x10306cd98 <col:19> 'int' lvalue Var 0x1030224d0 'soma1' 'int'
  `-ImplicitCastExpr 0x10306ce00 <col:27> 'int' <LValueToRValue>
  `-DeclRefExpr 0x10306cdc0 <col:27> 'int' lvalue Var 0x10306cc10 'soma2' 'int'
  )

42

[42 3 4 5 ]

[42 0]

#_(42) ; <==== Erro

`(42 20 3)
(println "oi")

; 40 + 2
(+ 40 2)

; 2 + 4 + 9 + 13 + 14
(+ 2 4 9 13 14)

; 4 + 5 x 3
(+ 4 (* 5 3))

; (4 + 5) x 3
(* (+ 4 5) 3)

; a evocação é da esquerda para direita.

; 5 x 5 - 4 x 2 x 3
(- (* 5 5) (* 4 2 3))

; (5 x 5) - (4 x 2 x 3)
(- (* 5 5) (* 4 2 3))

(def resultado (- (* 5 5) (* 4 2 3)))

resultado


(defn delta [a b c]
  (- (* b b) (* 4 a c)))

(delta 2 5 3)

(def resultado2 (delta 2 5 3))

resultado2







(defn dois []
  (println "2")
  2)

(defn tres []
  (println "3")
  3)

(defn cinco []
  (println "5")
  5)

dois
(dois)



(delta (dois) (cinco) (tres))

[1 2 3]
[]
(vec (range 10))

(let [pera 10]
  pera)

#_pera



(defn bhaskara [a b c]
  (let [d (delta a b c)
        raiz-delta (Math/sqrt d)
        numerador1  (+ (- b) raiz-delta)
        numerador2  (- (- b) raiz-delta)
        denominador (* 2 a)]
    [(/ numerador1 denominador)
     (/ numerador2 denominador)]))



(bhaskara 2 5 3)



(bhaskara 5 4 3)

(delta 5 4 3)

(delta (dois) (cinco) (tres))

(if (dois) (cinco) (tres))



(pos? 10)
(pos? 0)
(pos? -10)

(defn bhaskara-2 [a b c]
  (let [delta (delta a b c)]
    (if (pos? delta)
      (let [raiz-delta (Math/sqrt delta)
            numerador1  (+ (- b) raiz-delta)
            numerador2  (- (- b) raiz-delta)
            denominador (* 2 a)]
        [(/ numerador1 denominador)
         (/ numerador2 denominador)])
      :erro)))

(bhaskara-2 5 4 3)

(delta 1 2 1)

(bhaskara-2 1 2 1)

(defn bhaskara-3 [a b c]
  (let [delta (delta a b c)]
    (if (neg? delta)
      :erro
      (let [raiz-delta (Math/sqrt delta)
            numerador1  (+ (- b) raiz-delta)
            numerador2  (- (- b) raiz-delta)
            denominador (* 2 a)]
        (if (zero? delta)
          (/ numerador1 denominador)
          [(/ numerador1 denominador)
           (/ numerador2 denominador)])))))


(bhaskara-3 1 2 1)


(println "Hello world!")
`(println "Hello world!")


(defn a [x] (+ x 5))
(defn b [x] (- x 5))
(defn c [x] (* x 5))
(defn d [x] (/ x 5))

(d (c (b (a 10))))

(-> 10 a b c d)

(macroexpand-1 `(-> 10 a b c d))








(def sample-ps
  "     UID   PID  PPID  C    STIME TTY       TIME COMMAND
      root     0     0  0  Mar 27  ?        13:36 swapper
      root     1     0  0  Mar 27  ?         0:59 init
      root  1171     1  0  Mar 27  ?         1:10 /usr/sbin/utmpd
      root   193     0  0  Mar 27  ?         0:00 rng
      root  1275  1255  0  Mar 27  ?         0:00 /usr/sbin/evmchmgr -l /var/evm/adm/logfiles/evmchmgr.log
      root   354     1  0  Mar 27  ?         0:00 /sbin/fs/fsdaemon -f 0
      root  2749  2746  0  Mar 27  ?         0:01 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXRAIDSANativeIndicationProviderModule
      root  2762  2746  0  Mar 27  ?         1:44 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXLANIndicationProviderModule
      root  2764  2746  0  Mar 27  ?         2:27 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXStorageNativeProviderModule
  loliveir 21001 20995  0 19:40:10 ?         0:00 sar -uM 6 45
     sfmdb  2799  1596  0  Mar 27  ?         0:01 postgres: sfmdb RAIDSAPROVDB [local] idle
  sophosav  5973  5971  0  Apr 16  ?         4:28 mrouter -config router.config -ORBListenEndpoints iiop://:8193/ssl_port=8194 -name Router -certStore ./Certs.reg
    ")

sample-ps



(defn parse-header [header-str]
  (->> (s/split (s/lower-case header-str) #"\s+")
       (map keyword)))

(def data-row-regex #"(\w+)\s+(\d+)\s+(\d+)\s+(\d+)\s+((?:\w{3}\s{0,2}\d{1,2})|(?:\d\d:\d\d:\d\d)|(?:\d\d:\d\d))\s+([^\s]+)\s+([\d:]+)\s+(.+)$")

(defn split-data-row [data-row]
  (-> (re-find data-row-regex data-row)
      rest
      vec))


(defn transform-data-row [splited-row]
  (-> splited-row
      (update-in [1] edn/read-string)
      (update-in [2] edn/read-string)
      (update-in [3] edn/read-string)))

(defn make-hash-map [header transformed-data-row]
  (apply hash-map (interleave header transformed-data-row)))


; explicar partial antes.

(defn parse-ps [output]
  (let [[header-str & data] (s/split-lines (s/trim output) )]
    (let [header (parse-header header-str)]
      (->> data
           (map s/trim)
           (map split-data-row)
           (map transform-data-row)
           (map (partial make-hash-map header))
           #_(map (fn [x] (make-hash-map header x) ))
           ))))


(parse-ps sample-ps)

(def mais_cinco (partial + 5 ))

(mais_cinco 20)
















"https://www.youtube.com/watch?v=XnPQElUMRkg"





























(def grammar "
<S>            = <header> line* <ws>?
header         = <ws> 'UID' <ws> 'PID' <ws> 'PPID' <ws> 'C' <ws> 'STIME' <ws> 'TTY' <ws> 'TIME' <ws> cmd_header <lbreak>
cmd_header     = 'CMD'|'COMMAND'
line           = <ws> uid <ws> pid <ws> ppid <ws> c <ws> stime <ws> tty <ws> time <ws> cmd <lbreak?>
uid            = #'\\w+'
pid            = int
ppid           = int
c              = int
stime          = stime_wd_hh_m | stime_hh_m | stime_date | stime_time | stime_date_dd_mmm_yy
stime_wd_hh_m  = #'\\w{3}\\d{2}(A|P)M'
stime_hh_m     = #'\\d{1,2}:?\\d{2}(A|P)M'
stime_time     = #'\\d\\d:\\d\\d:\\d\\d'
stime_date     = #'\\w{3} \\d\\d'
stime_date_dd_mmm_yy     = #'\\d{2}\\w{3}\\d{2}'
tty            = #'[\\?\\w]+'
time           = #'[\\d:\\.]+'
cmd            = #'.+'
lbreak         = '\n'
int            = #'\\d+'
ws             = #'\\s+'
")

(def parser-ps-insta (insta/parser grammar))

(take 2 (parser-ps-insta output))


(def transformer
  (partial insta/transform
           {:int clojure.edn/read-string
            :stime_wd_hh_m identity
            :stime_hh_m identity
            :stime_time identity
            :stime_date identity
            :stime_date_dd_mmm_yy identity
            :line (fn [& args] (apply hash-map (flatten args)))}))

(defn parse-ps-2 [output]
  (->> (parser-ps-insta output)
       #_(take 3)
       transformer))

(parse-ps-2 sample-ps)


(require '[clojure.java.shell :refer [sh]])

(comment

  (-> (sh "ps" "-ef")
      :out
      parse-ps-2)
  )




(require '[chime :refer [chime-at]])
(require '[clj-time.core :as time.core])
(require '[clj-time.periodic :refer [periodic-seq]])



(take 10 (periodic-seq (time.core/now)
                      (-> 5 time.core/seconds)))


(defn take-selfie []
  (->> (sh "ps" "-ef")
       :out
       parse-ps-2
       (filter (fn [{cmd :cmd}]
                 (= cmd "ps -ef")))
       last
       :pid))

(def stop-scheduller (chime-at (periodic-seq (time.core/now)
                                             (-> 3 time.core/seconds))
                               (fn [time]
                                 (println (.toDate time) )
                                 (println (take-selfie) "\n"))))


(stop-scheduller)


(defmacro rev [fun & args]
  (cons fun (reverse args)))


(str (+ 1 2) " hi ")
(rev str (+ 1 2) " hi ")

(rev mod 3 10)
(macroexpand-1 `(rev mod 3 10))

(rev + 3 10 20)
(macroexpand-1 `(rev + 3 10 20))


(macroexpand '(rev str "hi" (+ 1 2)))


(require '[kibit.check :as kibit])
(require '[kibit.core])
(require '[clojure.core.logic.unifier :as unifier])
(require '[kibit.rules :as core-rules])

(def all-rules  (map unifier/prep core-rules/all-rules))

(kibit.core/simplify '(= x 0) all-rules)
(kibit.core/simplify '(+ x (+ y z)) all-rules)
(kibit.core/simplify '(not (empty? a)) all-rules)
(kibit.core/simplify '(Math/sqrt (+ (Math/pow x 2) (Math/pow y 2))) all-rules)






(comment

  ; http://blog.circleci.com/rewriting-your-test-suite-in-clojure-in-24-hours/
  ; https://github.com/circleci/translate-midje/blob/master/translate_midje.clj




  )

(def fib-seq
  ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+ a b)))))
    0N 1N))

(take 100 fib-seq)



(def m {:nome "Leandro" :dados {:idade 36 :sexo "m"}})

(update-in m [:dados :idade] inc)
(update-in {:a {:b 0}} [:a :b] inc)





(def v "Nexo Expresso
Nexo Explícito
Nexo Ensaio
Nexo Colunistas
Nexo Gráfico
Nexo Vídeo
Nexo Extra
Nexo Reportagem
Nexo Entrevista
Nexo Podcast
Nexo Agora
Nexo Serviço
Nexo Especial")

(def accents
  {\á "a"
   \à "a"
   \â "a"
   \ã "a"

   \é "e"
   \è "e"
   \ê "e"

   \í "i"
   \ì "i"
   \î "i"

   \ó "o"
   \ò "o"
   \ô "o"
   \õ "o"

   \ú "u"
   \ù "u"
   \û "u"

   \ç "c"
   })




;; Recurrency is a powerful concept, which helps us traverse
;; collections. We can also abstract the actual transformation,
;; (which is represented as `term` here) and the evaluation
;; of the next step (represented as `next`).

(defn sum [ term a next b ]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

;; Constructing new functions from other functions.
;; Remember, that operators are actually functions.

(defn cube [ n ]
  (* n n n))

(defn incr [ n ]
  (+ n 1))

;; Reusing the existing function inside the transformation.
;; Please, look that we describe what should be calculated,
;; not how it should iterate through range.

(defn sum-cubes [ a b ]
  (sum cube a incr b))

(println (sum-cubes 3 3))

;; Private functions can be created inside the scope.
;; Thanks to that we can hide unnecessary details from
;; the others.

(defn pi-sum [ a b ]
  (defn pi-term [ x ]
    (/ 1.0 (* x (+ x 2))))
  (defn pi-next [ x ]
    (+ x 4))
  (sum pi-term a pi-next b))

(println (* 8 (pi-sum 1 1000)))

;; Please look at the `add-dx` function - in that case
;; we are using function arguments inside from the higher
;; scope.

(defn integral [ f a b dx ]
  (defn add-dx [ x ] (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b) dx))

(println (integral cube 0 1 0.001))

















(defn first-denomization [ kinds-of-coins ]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))

(defn cc [ amount kinds-of-coins ]
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc amount
                     (- kinds-of-coins 1))
                 (cc (- amount
                        (first-denomization kinds-of-coins))
                     kinds-of-coins))))

(defn count-change [ amount ]
  (cc amount 2))

(println (count-change 10)  )





(->> (s/split-lines "wp_commentmeta
wp_comments
wp_links
wp_options
wp_postmeta
wp_posts
wp_term_relationships
wp_term_taxonomy
wp_terms
wp_usermeta
wp_users")
     (map #(str "DROP table " % "; "))
     (s/join))


"http://alcor.concordia.ca/~vjorge/Palavras-Cruzadas/Lista-de-Palavras.txt"


