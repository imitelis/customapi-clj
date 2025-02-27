(ns customapi.controllers.clothes
  (:require [clojure.spec.alpha :as s]
            [customapi.adapters.clothes :as ac]
            [customapi.db.clothes :as dc]
            [customapi.specs.clothes :as sc]
            [ring.util.response :as response]))

(defn add-cloth-controller [request]
  (let [new-cloth (:body-params request)
        valid-cloth (s/valid? ::sc/cloth-without-uuid new-cloth)]
    (cond
      (nil? new-cloth) (response/bad-request {:error "Cloth is missing"})
      (not valid-cloth) (response/not-found {:error "Cloth is invalid"})
      :else
      (do (dc/add-cloth new-cloth)
          (response/created "/clothes")))))

(defn get-cloth-controller [request]
  (let [uuid (:uuid (:path-params request))
        cloth-in-db (dc/get-a-cloth uuid)
        adapted-cloth (ac/cloth-adapter cloth-in-db)
        valid-cloth (s/valid? ::sc/cloth adapted-cloth)]
    (cond
      (nil? cloth-in-db) (response/not-found {:error "Cloth not found"})
      (not valid-cloth) (response/bad-request {:error "Cloth is invalid"})
      :else
      (response/response adapted-cloth))))

(defn remove-cloth-controller [request]
  (let [uuid (:uuid (:path-params request))
        cloth-in-db (dc/get-a-cloth uuid)]
    (if cloth-in-db
      (do (dc/delete-a-cloth uuid)
          {:status 204 :body nil})
      {:status 404 :body {:error "Cloth not found"}})))

(defn edit-cloth-controller [request]
  (let [uuid (:uuid (:path-params request))
        cloth (:body-params request)
        cloth-in-db (dc/get-a-cloth uuid)]
    (if cloth-in-db
      (do (dc/patch-a-cloth uuid cloth)
          {:status 200 :body nil})
      {:status 404 :body {:error "Cloth not found"}})))

#_((defn get-clothes-handler [request]
     (let [clothes (dc/get-clothes)]
       (if (seq clothes)
         (do (println "debugging" clothes)
             (response/response {:body {:clothes clothes}}))
         (response/not-found "not found")))))