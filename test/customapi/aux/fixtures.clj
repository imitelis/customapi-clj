(ns customapi.aux.fixtures
  (:require
   [customapi.db.core :as db]))

(defn db-fixture [f]
  (try
    (db/initialize-db!)
    (f)
    (finally
      (db/rollback!))))
