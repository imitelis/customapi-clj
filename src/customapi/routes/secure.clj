(ns customapi.routes.secure
  (:require [customapi.config.middlewares :as md]
            [customapi.controllers.secure :as cs]
            [customapi.specs.secure :as ss]))

(def secure-routes
  ["/secure"
   {:tags ["secure"]
    :openapi {:security [{"auth" []}]}
    :middleware [md/authentication-middleware]}

   ["/message"
    {:get {:summary "get a message"
           :parameters {:body nil}
           :responses {200 {:body ::ss/message}}
           :handler cs/get-message-controller}}]])