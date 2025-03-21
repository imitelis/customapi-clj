(ns customapi.routes.secure
  (:require [customapi.config.middlewares :as cm]
            [customapi.handlers.secure :as hs]
            [customapi.schemas.secure :as ss]))

(def secure-routes
  ["/secure"
   {:tags #{"secure"}
    :openapi {:security [{"auth" []}]}
    :swagger {:security [{"auth" []}]}
    :middleware [cm/authentication-middleware]}

   ["/secret"
    {:get {:summary "endpoint authenticated with a header"
           :responses {200 {:body ss/MessageResponse}
                       401 {:body ss/ErrorResponse}}
           :handler hs/secure-handler}}]

   ["/greeting"
    {:get {:summary "endpoint authenticated with a header"
           :responses {200 {:body ss/MessageResponse}
                       401 {:body ss/ErrorResponse}}
           :handler hs/greeting-handler}}]])