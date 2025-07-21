(ns customapi.db.core
  (:require [customapi.config.secrets :refer [secrets]]
            [mount.core :refer [defstate]]
            [next.jdbc :as njdbc]
            [ragtime.jdbc :as rjdbc]
            [ragtime.repl :as repl]))

(defn get-db-uri []
  (let [env (:env secrets)]
    (if (= env "test")
      (:db-uri-test secrets)
      (:db-uri secrets))))

(def ^:dynamic db-spec
  {:connection-uri (get-db-uri)})

(def migration-config
  {:datastore  (rjdbc/sql-database db-spec)
   :migrations (rjdbc/load-resources "migrations")})

(defn migrate! []
  (repl/migrate migration-config))

(defn rollback! []
  (repl/rollback migration-config))

(defstate conn
  :start (njdbc/get-datasource db-spec))

(defn init-db! []
  (migrate!))