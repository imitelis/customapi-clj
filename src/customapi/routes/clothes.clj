(ns customapi.routes.clothes
  (:require [customapi.schemas.clothes :as sc]
            [customapi.handlers.clothes :as hc]))

(def clothes-routes
  ["/clothes"

   {:tags #{"clothes"}}

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body sc/cloth}
                       400 {:body {:error string?}}
                       404 {:body {:error string?}}}
           :handler hc/retrieve-cloth-handler} 
     #_(
        :delete {:summary "Delete a cloth by UUID"
                 :parameters {:path {:uuid uuid?}}
                 :responses {204 {:body nil}
                             404 {:body {:error string?}}}
                 :handler hc/remove-cloth-handler}
     )
     }]])