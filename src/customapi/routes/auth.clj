(ns customapi.routes.auth
  (:require [customapi.handlers.auth :as ha]
            [customapi.schemas.auth :as sa]))

(def auth-routes
  ["/auth"

   {:tags #{"auth"}}

   ["/login"
    {:post {:summary "login with login params"
            :parameters {:body sa/login-params}
            :responses {200 {:body sa/login-response}}
            :handler ha/login-handler}}]
   ["/validate"
    {:get {:summary "get auth with token params"
           :parameters {:query sa/auth-param}
           :responses {200 {:body sa/auth-response}
                       401 {:body sa/error}}
           :handler ha/auth-handler}}]])