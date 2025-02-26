(ns customapi.config.database
  (:require [next.jdbc :as jdbc]))

(def db-spec {:dbtype "sqlite" :dbname "clothes.db"})

(defn create-tables []
  (jdbc/execute! db-spec
                 ["CREATE TABLE IF NOT EXISTS clothes (id INTEGER PRIMARY KEY, name TEXT, type TEXT, size TEXT)"]))

(defn initialize-db []
  (create-tables))

(defn get-clothes []
  (jdbc/execute! db-spec ["SELECT * FROM clothes"]))

(defn add-cloth [cloth]
  ;; (println "cloth type from db" (:type cloth))
  (jdbc/execute! db-spec
                 ["INSERT INTO clothes (name, type, size) VALUES (?, ?, ?)"
                  (:name cloth) (:type cloth) (:size cloth)]))