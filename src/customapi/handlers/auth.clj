(ns customapi.handlers.auth
  (:require [buddy.sign.jwt :as jwt]
            [customapi.config.secrets :refer [secrets]]))

(defn login-handler [request]
  (let [body-params (:body-params request)
        username {:username (:username body-params)}
        token (jwt/sign username (:jwt-key secrets) {:alg :hs256})]
    {:status 200 :body {:token token}}))

(defn auth-handler [request]
  (let [query-params (:query-params request)
        auth-token (get query-params "auth-token")
        payload (jwt/unsign auth-token (:jwt-key secrets))]
    {:status 200 :body payload}))