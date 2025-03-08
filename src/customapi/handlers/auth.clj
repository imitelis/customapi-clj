(ns customapi.handlers.auth
  (:require [customapi.config.jwt :refer [jwt-sign jwt-unsign]]))

(defn login-handler [request]
  (let [body-params (:body-params request)
        username {:username (:username body-params)}
        token (jwt-sign username)]
    {:status 200 :body {:token token}}))

(defn auth-handler [request]
  (let [query-params (:query-params request)
        auth-token (get query-params "auth-token")
        payload (jwt-unsign auth-token)]
    {:status 200 :body payload}))