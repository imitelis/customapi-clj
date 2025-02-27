(ns customapi.routes.clothes
  (:require [customapi.controllers.clothes :as cc]
            [customapi.specs.clothes :as cs]))

(def clothes-routes
  ["/clothes"
   {:tags ["clothes"]}

   ["/"
    {:post {:summary "Add a new cloth"
            :parameters {:body ::cs/cloth-without-uuid}
            :responses {201 {:body nil}
                        404 {:body {:error string?}}}
            :handler cc/add-cloth-controller}
     #_(:get {:summary "Retrieve clothes"
              :parameters {:body nil}
              :responses {200 {:body {:clothes ::cs/clothes-response-list}}
                          400 {:body {:error string?}}}
              :handler ch/get-clothes-handler})}]

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body ::cs/cloth}
                       400 {:body {:error string?}}}
           :handler cc/get-cloth-controller}}]])