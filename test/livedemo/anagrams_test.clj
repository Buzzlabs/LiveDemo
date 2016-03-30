(ns livedemo.anagrams-test
  (require [livedemo.anagrams :refer [anagrams]]
           [expectations :refer [expect]]
           [clojure.test :refer [deftest]]
           [clj-http.client :as http]))



(expect 1 (anagrams ["amor" "roma" "bicicleta"]))

(comment
  (->> (http/get words-url)
       :body
       )
  )