(ns customapi.routes.clothes
  (:require [customapi.specs.math :as ms]))

(def clothes-routes
  ["/clothes"
   {:tags ["clothes"]}

   ["/plus" 
    {:get {:summary "retrieve clothes"
           :parameters nil
           :responses {200 {:body ::ms/math-response}}
           :handler (fn [{{{:keys [x y]} :query} :parameters}]
                      {:status 200
                       :body {:total (+ x y)}})}}]])