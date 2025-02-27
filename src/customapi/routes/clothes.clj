(ns customapi.routes.clothes
  (:require [customapi.handlers.clothes :as ch]
            [customapi.specs.clothes :as cs]))

(def clothes-routes
  ["/clothes"
   {:tags ["clothes"]}

   ["/"
    {:post {:summary "Add a new cloth"
            :parameters {:body ::cs/cloth-parameter}
            :responses {201 {:body nil}
                        400 {:body {:error string?}}}
            :handler ch/add-cloth-handler}
     #_(:get {:summary "Retrieve clothes"
              :parameters {:body nil}
              :responses {200 {:body {:clothes ::cs/clothes-response-list}}
                          400 {:body {:error string?}}}
              :handler ch/get-clothes-handler})}]

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body ::cs/clothes-response}}
           :handler ch/get-cloth-handler}}]])