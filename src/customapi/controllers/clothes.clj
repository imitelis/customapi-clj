(ns customapi.controllers.clothes
  (:require [clojure.spec.alpha :as s]
            [customapi.adapters.clothes :as ac]
            [customapi.db.clothes :as dc]
            [customapi.specs.clothes :as sc]
            [ring.util.response :as response]))

(defn add-cloth-controller [request]
  (let [cloth (:body-params request)]
    (if cloth
      (let [valid-cloth (s/valid? ::sc/cloth-without-uuid cloth)]
        (if valid-cloth
          (do
            (dc/add-cloth cloth)
            (response/created "/clothes"))
          (response/bad-request {:error "Cloth is invalid"})))
      (response/bad-request {:error "Cloth is missing"}))))

(defn get-cloth-controller [request]
  (let [uuid (:uuid (:path-params request))
        cloth (dc/get-a-cloth uuid)]
    (if cloth
      (let [adapted-cloth (ac/cloth-adapter cloth)]
        (if (s/valid? ::sc/cloth adapted-cloth)
          (response/response adapted-cloth)
          (response/bad-request {:error "Cloth is invalid"})))
      (response/bad-request {:error "Cloth not found"}))))

#_((defn get-clothes-handler [request]
     (let [clothes (dc/get-clothes)]
       (if (seq clothes)
         (do (println "debugging" clothes)
             (response/response {:body {:clothes clothes}}))
         (response/not-found "not found")))))