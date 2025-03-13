(ns customapi.config.secrets
  (:require [clojure.edn :as edn]))

(def secrets (edn/read-string (slurp "resources/config.edn")))