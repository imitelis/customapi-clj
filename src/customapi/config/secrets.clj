(ns customapi.config.secrets
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(def secrets
  (with-open [r (io/reader (io/resource "config.edn"))]
    (let [pushback-reader (java.io.PushbackReader. r)]
      (edn/read pushback-reader))))