(ns customapi.db.clothes
  (:require [customapi.db.core :as dc]
            [next.jdbc :as jdbc]))

(defn add-a-cloth [cloth-without-uuid]
  (let [cloth-uuid (str (java.util.UUID/randomUUID))
        cloth cloth-without-uuid]
    (jdbc/execute!
     dc/db-spec
     ["INSERT INTO clothes (uuid, name, type, size) VALUES (?, ?, ?, ?)"
      cloth-uuid (:name cloth) (:type cloth) (:size cloth)])
    cloth-uuid))

(defn get-clothes [clothes-name clothes-type]
  (let [base-query "SELECT * FROM clothes"
        query-params (cond-> []
                       clothes-name (conj ["name LIKE ?" (str "%" clothes-name "%")])
                       clothes-type (conj ["type LIKE ?" (str "%" clothes-type "%")]))
        final-query (if (empty? query-params)
                      base-query
                      (str base-query " WHERE " (String/join " AND " (map first query-params))))]
    ;; (println "Final query:" final-query)
    (jdbc/execute! dc/db-spec (concat [final-query] (map second query-params)))))

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