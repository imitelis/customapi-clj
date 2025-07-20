(ns customapi.aux.fixtures
  (:require [customapi.db.core :as db]
            [mount.core :as mount]))

(defn db-fixture [f]
  (try
    (mount/start)
    (db/initialize-db!)
    (f)
    (finally
      (db/rollback!))))
