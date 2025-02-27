(ns customapi.db.clothes
  (:require [customapi.db.core :as dc]
            [next.jdbc :as jdbc]))

(defn add-cloth [cloth-without-uuid]
  (let [cloth-uuid (str (java.util.UUID/randomUUID))
        cloth cloth-without-uuid]
    (jdbc/execute!
     dc/db-spec
     ["INSERT INTO clothes (uuid, name, type, size) VALUES (?, ?, ?, ?)"
      cloth-uuid (:name cloth) (:type cloth) (:size cloth)])))

(defn get-a-cloth [uuid]
  (let [result (jdbc/execute! dc/db-spec ["SELECT * FROM clothes WHERE uuid = ?" uuid])]
    (first result)))

(defn delete-a-cloth [uuid]
  (let [result (jdbc/execute! dc/db-spec ["DELETE FROM clothes WHERE uuid = ?" uuid])]
    (first result)))

(defn patch-a-cloth [cloth-uuid cloth-to-edit]
  (let [uuid cloth-uuid
        cloth cloth-to-edit]
    (jdbc/execute!
     dc/db-spec
     ["UPDATE clothes SET name = ?, type = ?, size = ? WHERE uuid = ?"
      (:name cloth) (:type cloth) (:size cloth) uuid])))