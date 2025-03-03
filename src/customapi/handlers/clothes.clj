(ns customapi.handlers.clothes
  (:require [malli.core :as m]
            [customapi.adapters.clothes :as ac]
            [customapi.db.clothes :as dc]
            [customapi.schemas.clothes :as sc]))



#_(
   (defn add-cloth-handler [request]
     (let [new-cloth (:body-params request)
           valid-cloth (m/validate sc/cloth-without-uuid new-cloth)]
       (cond
         (nil? new-cloth) {:status 404 :body {:error "Cloth is missing"}}
         (not valid-cloth) {:status 400 :body {:error "Cloth is invalid"}}
         :else
         (let [cloth-uuid (dc/add-a-cloth! new-cloth)
               created-cloth (assoc new-cloth :uuid cloth-uuid)
               adapted-cloth (ac/cloth-adapter created-cloth)]
           {:status 201 :body adapted-cloth}))))
   
   (defn retrieve-clothes-handler [request]
     (let [query-params (:query-params request)
           clothes-name (get query-params "clothes-name")
           clothes-type (get query-params "clothes-type")
           clothes (dc/get-clothes! clothes-name clothes-type)]
       (if (seq clothes)
         (let [adapted-clothes (ac/clothes-adapter clothes)
               valid-clothes (m/validate sc/clothes adapted-clothes)]
           (if valid-clothes
             {:status 200 :body adapted-clothes}
             {:status 400 :body {:error "Clothes are invalid"}}))
         {:status 404 :body {:error "Clothes not found"}})))

)

(defn retrieve-cloth-handler [request]
  (let [uuid (:uuid (:path-params request))
        cloth-in-db (dc/get-a-cloth! uuid)]
    (if (not cloth-in-db)
      {:status 404 :body {:error "Cloth not found"}}
      (let [adapted-cloth (ac/cloth-adapter cloth-in-db)
            valid-cloth (m/validate sc/cloth adapted-cloth)]
        (cond
          (not valid-cloth) {:status 400 :body {:error "Cloth is invalid"}}
          :else {:status 200 :body adapted-cloth})))))

#_(
   (defn remove-cloth-handler [request]
     (let [uuid (:uuid (:path-params request))
           cloth-in-db (dc/get-a-cloth! uuid)]
       (if cloth-in-db
         (do (dc/delete-a-cloth! uuid)
             {:status 204 :body nil})
         {:status 404 :body {:error "Cloth not found"}})))
   
   (defn edit-cloth-handler [request]
     (let [uuid (:uuid (:path-params request))
           cloth (:body-params request)
           cloth-in-db (dc/get-a-cloth! uuid)]
       (if cloth-in-db
         (do (dc/patch-a-cloth! uuid cloth)
             (let [edited-cloth (dc/get-a-cloth! uuid)
                   adapted-cloth (ac/cloth-adapter edited-cloth)]
               {:status 200 :body adapted-cloth}))
         {:status 404 :body {:error "Cloth not found"}})))
)