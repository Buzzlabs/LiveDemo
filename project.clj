(defproject livedemo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [midje "1.6.3"]
                 [expectations "2.0.9"]
                 [instaparse "1.4.0"]
                 [jarohen/chime "0.1.6"]
                 [jonase/kibit "0.1.2"]
                 ]
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :username [:env/my_datomic_user :gpg]
                                   :password [:env/my_datomic_password :gpg]}}
            )
