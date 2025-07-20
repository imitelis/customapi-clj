(defproject customapi "0.1.0"
  :description "Reitit OpenAPI Custom API"
  :url "http://github.com/imitelis/customapi"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[environ "1.2.0"]
                 [buddy/buddy-sign "3.4.1"]
                 [metosin/reitit "0.8.0-alpha1"]
                 [metosin/jsonista "0.3.8"]
                 [metosin/ring-swagger-ui "5.9.0"] 
                 [ring/ring-core "1.12.0"]
                 [ring/ring-mock "0.4.0"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [ring-cors/ring-cors "0.1.13"]
                 [ring-logger/ring-logger "1.1.1"]
                 [javax.servlet/servlet-api "2.5"]
                 [dev.weavejester/ragtime "0.11.0"]
                 [ch.qos.logback/logback-classic "1.2.6"]
                 [com.github.seancorfield/honeysql "2.7.1310"]
                 [com.github.seancorfield/next.jdbc "1.3.994"]
                 [org.clojure/clojure "1.11.2"]
                 [org.clojure/data.json "2.5.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.slf4j/slf4j-api "1.7.32"]
                 [org.xerial/sqlite-jdbc "3.39.2.0"]
                 [org.apache.commons/commons-fileupload2-core "2.0.0-M2"]]
  :main customapi.server
  :aot [customapi.server]
  :target-path "target/%s"
  :uberjar-name "customapi.jar"
  :repl-options {:init-ns customapi.server}
  :aliases {"unit"        ["test" ":unit"]
            "integration" ["test" ":integration"]
            "lint"        ["do" ["cljfmt" "check"] ["nsorg"] ["kibit"]]
            "lint-fix"    ["do" ["cljfmt" "fix"] ["nsorg" "--replace"]]}
  :test-selectors {:unit (fn [m] (.contains (str (:ns m)) "unit"))
                   :integration (fn [m] (.contains (str (:ns m)) "integration"))}
  :plugins [[lein-environ "1.2.0"]
            [lein-nsorg "0.3.0" :exclusions [org.clojure/clojure]]
            [lein-cljfmt "0.6.4" :exclusions [org.clojure/clojure]]]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})