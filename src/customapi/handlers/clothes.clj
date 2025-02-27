(ns customapi.handlers.clothes
  (:require [customapi.db.clothes :as dc]
            [ring.util.response :as response]))

(defn add-cloth-handler [request]
  (let [cloth (:body-params request)]
    (if cloth
      (do (dc/add-cloth cloth)
          (response/created "/clothes"))
      (response/bad-request "invalid body"))))

(defn get-cloth-handler [request]
  (let [uuid (:uuid (:path-params request))
        cloth (dc/get-a-cloth uuid)]
    (if cloth
      (response/response {:uuid (:clothes/uuid cloth)
                          :name (:clothes/name cloth)
                          :type (:clothes/type cloth)
                          :size (Long/parseLong (:clothes/size cloth))})
      (response/bad-request {:error "Cloth not found"}))))

#_((defn get-clothes-handler [request]
     (let [clothes (dc/get-clothes)]
       (if (seq clothes)
         (do (println "debugging" clothes)
             (response/response {:body {:clothes clothes}}))
         (response/not-found "not found"))))
   )