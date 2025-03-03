(ns customapi.routes.secure
  (:require [customapi.schemas.secure :as ss]
            [customapi.handlers.secure :as hs]))

(def secure-routes
  ["/secure"
   {:tags #{"secure"}
    :openapi {:security [{"auth" []}]}
    :swagger {:security [{"auth" []}]}}
   ["/get"
    {:get {:summary "endpoint authenticated with a header"
           :responses {200 {:body ss/secret-response}
                       401 {:body ss/error-response}}
           :handler hs/secure-handler}}]])