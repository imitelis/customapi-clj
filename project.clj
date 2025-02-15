(defproject customapi-clj "0.1.0"
  :description "Reitit Ring App with Swagger"
  :dependencies [[org.clojure/clojure "1.11.2"] 
                 [org.clojure/spec.alpha "0.5.238"]
                 [org.clojure/tools.logging "1.2.4"]
                 [ring/ring-mock "0.4.0"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [metosin/malli "0.10.0"]
                 [metosin/reitit "0.8.0-alpha1"]
                 [metosin/ring-swagger-ui "5.9.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [com.github.seancorfield/next.jdbc "1.3.994"]
                 [org.xerial/sqlite-jdbc "3.39.2.0"]
                 ;; [org.slf4j/slf4j-api "1.7.32"] 
                 ;; [ch.qos.logback/logback-classic "1.2.6"]
                 ;; [com.fzakaria/slf4j-timbre "0.2"]
                 [prismatic/schema "1.4.1"]]
  :main customapi.server
  :aot [customapi.server]
  :repl-options {:init-ns customapi.server}
  ;; :jvm-opts ["-Dclojure.tools.logging.factory=clojure.tools.logging.impl/slf4j-factory"]
  :plugins [[lein-nsorg "0.3.0" :exclusions [org.clojure/clojure]]
            [lein-cljfmt "0.6.4" :exclusions [org.clojure/clojure]]]
  :aliases {"lint" ["do" ["cljfmt" "fix"] ["nsorg" "--replace"]]})