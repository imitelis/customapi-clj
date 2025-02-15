(ns customapi.handlers.clothes
  (:require [customapi.config.database :refer [db-spec]]
            [next.jdbc :as jdbc]))

(defn get-clothes []
  (jdbc/execute! db-spec ["SELECT * FROM clothes"]))