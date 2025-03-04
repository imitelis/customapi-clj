(ns customapi.routes.clothes
  (:require [customapi.handlers.clothes :as hc]
            [customapi.schemas.clothes :as sc]))

(def clothes-routes
  ["/clothes"

   {:tags #{"clothes"}}

   ["/"
    {:post {:summary "Add a new cloth"
            :parameters {:body sc/cloth-without-uuid}
            :responses {201 {:body sc/cloth}
                        400 {:body {:error string?}}
                        404 {:body {:error string?}}}
            :handler hc/add-cloth-handler}
     :get {:summary "Get clothes"
           :parameters {:query sc/clothes-query}
           :responses {200 {:body sc/clothes}
                       404 {:body {:error string?}}}
           :handler hc/retrieve-clothes-handler}}]

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body sc/cloth}
                       400 {:body {:error string?}}
                       404 {:body {:error string?}}}
           :handler hc/retrieve-cloth-handler}
     :delete {:summary "Delete a cloth by UUID"
              :parameters {:path {:uuid uuid?}}
              :responses {204 {:body {}}
                          404 {:body {:error string?}}}
              :handler hc/remove-cloth-handler}
     :patch {:summary "Patch a cloth by UUID"
             :parameters {:path {:uuid uuid?}
                          :body sc/cloth-without-uuid}
             :responses {200 {:body sc/cloth}
                         400 {:body {:error string?}}
                         404 {:body {:error string?}}}
             :handler hc/edit-cloth-handler}}]])