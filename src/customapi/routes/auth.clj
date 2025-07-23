(ns customapi.routes.auth
  (:require [customapi.handlers.auth :as ha]
            [customapi.schemas.auth :as sa]))

(def auth-routes
  ["/api/auth"

   {:tags #{"auth"}}

   ["/login"
    {:post {:summary "login with login params"
            :parameters {:body sa/LoginParams}
            :responses {200 {:body sa/LoginResponse}}
            :handler ha/login-handler}}]
   ["/validate"
    {:get {:summary "get auth with token params"
           :parameters {:query sa/AuthParam}
           :responses {200 {:body sa/AuthResponse}
                       401 {:body sa/ErrorResponse}}
           :handler ha/auth-handler}}]])