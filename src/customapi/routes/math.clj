(ns customapi.routes.math
  (:require [customapi.handlers.math :as hm]
            [customapi.schemas.math :as sm]))

(def math-routes
  ["/math"

   {:tags #{"math"}}

   ["/plus"
    {:get {:summary "plus with query parameters"
           :parameters {:query sm/two-ope-params}
           :responses {200 {:body sm/total-response}}
           :handler hm/plus-query-handler}
     :post {:summary "plus with body parameters"
            :parameters {:body sm/two-ope-params}
            :responses {200 {:body sm/total-response}}
            :handler hm/plus-body-handler}}]])