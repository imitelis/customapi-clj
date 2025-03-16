(ns customapi.db.core
  (:require [customapi.config.secrets :refer [secrets]]
            [next.jdbc :as jdbc]))

(defn get-db-name []
  (let [config secrets
        env (System/getenv "ENV")
        db-name (if (= env "test")
                  "clothes_test.db"
                  (:db-name config))]
    db-name))

(def db-spec
  {:dbtype (:db-type secrets)
   :dbname (get-db-name)})

(println (:dbname db-spec))

(defn create-tables! []
  (jdbc/execute! db-spec
                 ["CREATE TABLE IF NOT EXISTS clothes (uuid TEXT PRIMARY KEY, name TEXT, type TEXT, size TEXT)"]))

(defn initialize-db! []
  (create-tables!))