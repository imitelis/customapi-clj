(ns customapi.routes.clothes
  (:require [customapi.handlers.clothes :as hc]
            [customapi.specs.clothes :as cs]))

(def clothes-routes
  ["/clothes"
   {:tags ["clothes"]}

   ["/"
    {:post {:summary "Add a new cloth"
            :parameters {:body ::cs/cloth-without-uuid}
            :responses {201 {:body ::cs/cloth}
                        400 {:body {:error string?}}
                        404 {:body {:error string?}}}
            :handler hc/add-cloth-handler}
     :get {:summary "Get clothes"
           :parameters {:query ::cs/clothes-query}
           :responses {200 {:body ::cs/clothes}
                       404 {:body {:error string?}}}
           :handler hc/retrieve-clothes-handler}}]

   ["/:uuid"
    {:get {:summary "Get a cloth by UUID"
           :parameters {:path {:uuid uuid?}}
           :responses {200 {:body ::cs/cloth}
                       400 {:body {:error string?}}
                       404 {:body {:error string?}}}
           :handler hc/retrieve-cloth-handler}
     :delete {:summary "Delete a cloth by UUID"
              :parameters {:path {:uuid uuid?}}
              :responses {204 {:body nil}
                          404 {:body {:error string?}}}
              :handler hc/remove-cloth-handler}
     :patch {:summary "Patch a cloth by UUID"
             :parameters {:path {:uuid uuid?}
                          :body ::cs/cloth-without-uuid}
             :responses {200 {:body ::cs/cloth}
                         400 {:body {:error string?}}
                         404 {:body {:error string?}}}
             :handler hc/edit-cloth-handler}}]])