(ns customapi.config.secrets
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [environ.core :refer [env]]))

(defn load-config-edn []
  (let [path (or (env :config-path) (System/getenv "CONFIG_PATH"))
        rdr  (or (some-> path io/file io/reader)
                 (some-> "config.edn" io/resource io/reader))]
    (if rdr
      (with-open [r rdr]
        (edn/read (java.io.PushbackReader. r)))
      (throw (ex-info "Missing config.edn or CONFIG_PATH" {})))))

(defn load-env-vars []
  (merge
   (into {}
         (for [[k v] (System/getenv)]
           [(keyword (string/lower-case (string/replace k "_" "-"))) v]))
   env)) ;; lein-environ values already keywordized

(defn parse-number-if-applicable [v]
  (cond
    (string? v)
    (let [trimmed (string/trim v)]
      (cond
        (re-matches #"^-?\d+$" trimmed) (Integer/parseInt trimmed)
        (re-matches #"^-?\d+\.\d+$" trimmed) (Double/parseDouble trimmed)
        :else v))
    :else v))

;; env > config.edn
(def secrets
  (let [file-config (load-config-edn)
        env-vars    (load-env-vars)
        env-vars-parsed (into {}
                              (map (fn [[k v]] [k (parse-number-if-applicable v)]))
                              env-vars)]
    (merge file-config env-vars-parsed)))
