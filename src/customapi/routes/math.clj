(ns customapi.routes.math
  (:require [customapi.specs.math :as ms]
            [customapi.utils.math :as mu]))

(def math-routes
  ["/math"
   {:tags ["math"]}

   ["/plus"
    {:get {:summary "plus with spec query parameters"
           :parameters {:query ::ms/math-two-params}
           :responses {200 {:body ::ms/math-response}}
           :handler (fn [{{{:keys [x y]} :query} :parameters}]
                      {:status 200
                       :body {:total (+ x y)}})}
     :post {:summary "plus with spec body parameters"
            :parameters {:body ::ms/math-two-params}
            :responses {200 {:body ::ms/math-response}}
            :handler (fn [{{{:keys [x y]} :body} :parameters}]
                       {:status 200
                        :body {:total (+ x y)}})}}]
   ["/multiply"
    {:post {:summary "multiply with spec body parameters"
            :parameters {:body ::ms/math-two-params}
            :responses {200 {:body ::ms/math-response}}
            :handler (fn [{{{:keys [x y]} :body} :parameters}]
                       {:status 200
                        :body {:total (* x y)}})}}]

   ["/factorial"
    {:post {:summary "factorial with spec body parameter"
            :parameters {:body ::ms/math-one-param}
            :responses {200 {:body ::ms/math-response}}
            :handler (fn [{{{:keys [x]} :body} :parameters}]
                       {:status 200
                        :body {:total (mu/factorial x)}})}}]

   ["/fibonacci"
    {:post {:summary "fibonacci with spec body parameter"
            :parameters {:body ::ms/math-one-param}
            :responses {200 {:body ::ms/math-response}}
            :handler (fn [{{{:keys [z]} :body} :parameters}]
                       {:status 200
                        :body {:total (mu/fibonacci z)}})}}]])
