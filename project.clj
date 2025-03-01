(defproject customapi "0.1.0"
  :description "Reitit OpenAPI Custom API"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.slf4j/slf4j-api "1.7.32"]
                 [org.xerial/sqlite-jdbc "3.39.2.0"]
                 [org.clojure/clojure "1.11.2"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.clojure/spec.alpha "0.5.238"]
                 [prismatic/schema "1.4.1"]
                 [ring/ring-core "1.12.0"]
                 [ring/ring-mock "0.4.0"]
                 [ring/ring-json "0.5.1"] 
                 [ring/ring-jetty-adapter "1.12.1"]
                 [ring-cors/ring-cors "0.1.13"]
                 [ring-logger/ring-logger "1.1.1"]
                 [metosin/malli "0.10.0"]
                 [metosin/reitit "0.8.0-alpha1"]
                 [metosin/ring-swagger-ui "5.9.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [org.apache.commons/commons-fileupload2-core "2.0.0-M2"]
                 [com.github.seancorfield/next.jdbc "1.3.994"]
                 [ch.qos.logback/logback-classic "1.2.6"]]
  :main customapi.server
  :aot [customapi.server]
  :target-path "target/%s" 
  :repl-options {:init-ns customapi.server}
  :aliases {"lint" ["do" ["cljfmt" "fix"] ["nsorg" "--replace"]]}
  :plugins [[lein-nsorg "0.3.0" :exclusions [org.clojure/clojure]]
            [lein-cljfmt "0.6.4" :exclusions [org.clojure/clojure]]]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})