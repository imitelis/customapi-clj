(ns customapi.controllers.clothes
  (:require [clojure.spec.alpha :as s]
            [customapi.adapters.clothes :as ac]
            [customapi.db.clothes :as dc]
            [customapi.specs.clothes :as sc]))

(defn add-cloth-controller [request]
  (let [new-cloth (:body-params request)
        valid-cloth (s/valid? ::sc/cloth-without-uuid new-cloth)]
    (cond
      (nil? new-cloth) {:status 404 :body {:error "Cloth is missing"}}
      (not valid-cloth) {:status 400 :body {:error "Cloth is invalid"}}
      :else
      (let [cloth-uuid (dc/add-a-cloth new-cloth)
            created-cloth (assoc new-cloth :uuid cloth-uuid)
            adapted-cloth (ac/cloth-adapter created-cloth)]
        {:status 201 :body adapted-cloth}))))

(defn retrieve-clothes-controller [_]
  (let [clothes (dc/get-clothes)
        adapted-clothes (ac/clothes-adapter clothes)
        valid-clothes (s/valid? ::sc/clothes adapted-clothes)]
    (if valid-clothes
      {:status 200 :body adapted-clothes}
      {:status 404 :body {:error "Clothes not found"}})))

(defn retrieve-cloth-controller [request]
  (let [uuid (:uuid (:path-params request))
        cloth-in-db (dc/get-a-cloth uuid)]
    (if (not cloth-in-db)
      {:status 404 :body {:error "Cloth not found"}}
      (let [adapted-cloth (ac/cloth-adapter cloth-in-db)
            valid-cloth (s/valid? ::sc/cloth adapted-cloth)]
        (cond
          (not valid-cloth) {:status 400 :body {:error "Cloth is invalid"}}
          :else {:status 200 :body adapted-cloth})))))

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
          (let [edited-cloth (dc/get-a-cloth uuid)
                adapted-cloth (ac/cloth-adapter edited-cloth)] 
            {:status 200 :body adapted-cloth}))
      {:status 404 :body {:error "Cloth not found"}})))