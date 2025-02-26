(ns customapi.handlers.clothes
  (:require [customapi.config.database :as db]
            [ring.util.response :as response]))

(defn get-clothes-handler []
  (let [clothes (db/get-clothes)]
    {:body {:clothes clothes}}))

(defn add-cloth-handler [request]
  (println "debugging" (:body-params request))
  (let [cloth (:body-params request)]
    (if cloth
      (do (db/add-cloth cloth)
          (response/created "/clothes"))
      (response/bad-request "invalid body"))))