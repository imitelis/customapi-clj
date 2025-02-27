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
                        400 {:body {:error string?}}
                        404 {:body {:error string?}}}
            :handler cc/add-cloth-controller}
     :get {:summary "Get clothes"
           :parameters {:body nil}
           :responses {200 {:body ::cs/clothes}
                       404 {:body {:error string?}}}
           :handler cc/retrieve-clothes-controller}}]

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body ::cs/cloth}
                       400 {:body {:error string?}}
                       404 {:body {:error string?}}}
           :handler cc/retrieve-cloth-controller}
     :delete {:summary "Delete a cloth by UUID"
              :parameters {:path {:uuid uuid?}}
              :responses {204 {:body nil}
                          404 {:body {:error string?}}}
              :handler cc/remove-cloth-controller}
     :patch {:summary "Patch a cloth by UUID"
             :parameters {:path {:uuid uuid?}
                          :body ::cs/cloth-without-uuid}
             :responses {200 {:body ::cs/cloth}
                         400 {:body {:error string?}}
                         404 {:body {:error string?}}}
             :handler cc/edit-cloth-controller}}]])