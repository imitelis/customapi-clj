(ns customapi.db.core
  (:require [customapi.config.secrets :refer [secrets]]
            [next.jdbc :as jdbc]))

(def db-spec
  {:dbtype (:db-type secrets)
   :dbname (:db-name secrets)})

(defn create-tables []
  (jdbc/execute! db-spec
                 ["CREATE TABLE IF NOT EXISTS clothes (uuid TEXT PRIMARY KEY, name TEXT, type TEXT, size TEXT)"]))

(defn initialize-db []
  (create-tables))