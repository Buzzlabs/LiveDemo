(ns livedemo.regular-expressions-test
  (:require [livedemo.regular-expressions :refer :all]
            [clojure.test :refer :all]))


(deftest transations-map
  (is (= {:eof true}  (transition-map "") ))
  (is (= {:eof true \a false} (transition-map "a") ))
  (is (= {:eof true \a false} (transition-map "ab") ))
  (is (= {:eof true \a true} (states "a?") ))

  )
