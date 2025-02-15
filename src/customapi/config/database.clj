(ns customapi.config.database
  (:require [next.jdbc :as jdbc]))

(def db-spec {:dbtype "sqlite" :dbname "clothes.db"})

(defn create-tables []
  (jdbc/execute! db-spec
                 ["CREATE TABLE IF NOT EXISTS clothes (id INTEGER PRIMARY KEY, name TEXT, type TEXT, size TEXT)"]))

(defn initialize-db []
  (create-tables))
