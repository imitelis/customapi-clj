(ns customapi.routes.math
  (:require [customapi.handlers.math :as hm]
            [customapi.schemas.math :as sm]))

(def math-routes
  ["/math"

   {:tags #{"math"}}

   ["/plus"
    {:get {:summary "plus with query parameters"
           :parameters {:query sm/TwoOpParams}
           :responses {200 {:body sm/TotalResponse}}
           :handler hm/plus-query-handler}
     :post {:summary "plus with body parameters"
            :parameters {:body sm/TwoOpParams}
            :responses {200 {:body sm/TotalResponse}}
            :handler hm/plus-body-handler}}]])