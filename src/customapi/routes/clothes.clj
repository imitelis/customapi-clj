(ns customapi.routes.clothes
  (:require [customapi.specs.clothes :as cs]
            [customapi.handlers.clothes :as ch]))

(def clothes-routes
  ["/clothes"
   {:tags ["clothes"]}

   ["/"
    {:post {:summary "Add a new cloth"
            :parameters {:body ::cs/cloth-parameter}
            :responses {201 {:body nil}  ; 201 Created response
                        400 {:body {:error string?}}}  ; 400 Bad Request response for invalid data
            :handler ch/add-cloth-handler}}]])