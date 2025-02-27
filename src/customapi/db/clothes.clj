(ns customapi.db.clothes
  (:require [customapi.db.core :as dc]
            [next.jdbc :as jdbc]))

(defn add-cloth [cloth]
  (let [cloth-uuid (str (java.util.UUID/randomUUID))]
    (jdbc/execute! dc/db-spec
                   ["INSERT INTO clothes (uuid, name, type, size) VALUES (?, ?, ?, ?)"
                    cloth-uuid (:name cloth) (:type cloth) (:size cloth)])))

(defn get-a-cloth [uuid]
  (let [result (jdbc/execute! dc/db-spec ["SELECT * FROM clothes WHERE uuid = ?" uuid])]
    (first result)))