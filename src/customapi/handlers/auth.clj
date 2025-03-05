(ns customapi.handlers.auth
  (:require [buddy.sign.jwt :as jwt]))

(def secret-key "your-secret-key")

(defn login-handler [request]
  (let [body-params (:body-params request)
        username {:username (:username body-params)}
        token (jwt/sign username secret-key {:alg :hs256})]
    {:status 200 :body {:token token}}))

(defn auth-handler [request]
  (let [query-params (:query-params request)
        auth-token (get query-params "auth-token")
        payload (jwt/unsign auth-token secret-key)]
    {:status 200 :body payload}))