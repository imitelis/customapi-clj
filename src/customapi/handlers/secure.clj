(ns customapi.handlers.secure
  (:require [buddy.sign.jwt :as jwt]
            [customapi.config.secrets :refer [secrets]]))

(defn secure-handler [_]
  {:status 201 :body {:message "I'm a secret!"}})

(defn greeting-handler [request]
  (let [auth-header (get-in request [:headers "auth-header"])
        payload (jwt/unsign auth-header (:jwt-key secrets) {:alg :hs256})
        username (:username payload)]
    {:status 201 :body {:message (str "And your name is " username)}}))