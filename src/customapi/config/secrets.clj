(ns customapi.config.secrets
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [environ.core :refer [env]]))

(defn read-config-edn []
  (with-open [r (io/reader (or (env :config-path) (io/resource "config.edn")))]
    (let [pushback-reader (java.io.PushbackReader. r)]
      (edn/read pushback-reader))))

(def secrets
  (let [file-config (read-config-edn)]
    (merge
     file-config
     {:host (or (env :host) (:host file-config))
      :port (Integer/parseInt (or (env :port) (str (:port file-config))))
      :allowed-host (or (env :allowed_host) (:allowed-host file-config))
      :allowed-origin (or (env :allowed_origin) (:allowed-origin file-config))
      :db-type (or (env :db_type) (:db_type file-config))
      :db-name (or (env :db_name) (:db_name file-config))
      :jwt-key (or (env :jwt_key) (:jwt_key file-config))})))