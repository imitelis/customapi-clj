(ns customapi.db.clothes
  (:require [customapi.db.core :refer [db-spec]]
            [honey.sql :as sql]
            [honey.sql.helpers :as h]
            [next.jdbc :as jdbc]))

(defn add-a-cloth! [cloth-without-uuid]
  (let [cloth-uuid (str (java.util.UUID/randomUUID))
        cloth (assoc cloth-without-uuid :uuid cloth-uuid)
        insert-map (h/insert-into :clothes)
        insert-map (h/values insert-map [cloth])
        sql-map (sql/format insert-map)]
    (jdbc/execute! db-spec sql-map)
    cloth-uuid))

(defn get-clothes! [clothes-name clothes-type clothes-size]
  (let [conditions (cond-> []
                     clothes-name (conj [:like :name (str "%" clothes-name "%")])
                     clothes-type (conj [:like :type (str "%" clothes-type "%")])
                     clothes-size (conj [:= :size clothes-size]))
        sql-map (-> (h/select :*)
                    (h/from :clothes)
                    (cond-> (seq conditions) (h/where [:and conditions]))
                    sql/format)]
    (jdbc/execute! db-spec sql-map)))

(defn get-a-cloth! [uuid]
  (let [sql-map (-> (h/select :*)
                    (h/from :clothes)
                    (h/where [:= :uuid uuid])
                    sql/format)
        result (jdbc/execute! db-spec sql-map)]
    (first result)))

(defn delete-a-cloth! [uuid]
  (let [sql-map (-> (h/delete-from :clothes)
                    (h/where [:= :uuid uuid])
                    sql/format)
        result (jdbc/execute! db-spec sql-map)]
    (first result)))

(defn patch-a-cloth! [cloth-uuid cloth-to-edit]
  (let [sql-map (-> (h/update :clothes)
                    (h/set {:name (:name cloth-to-edit)
                            :type (:type cloth-to-edit)
                            :size (:size cloth-to-edit)})
                    (h/where [:= :uuid cloth-uuid])
                    sql/format)]
    (jdbc/execute! db-spec sql-map)))