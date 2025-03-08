(ns customapi.config.jwt
  (:require [buddy.sign.jwt :as jwt]
            [customapi.config.secrets :refer [secrets]]))

(defn jwt-sign [username]
  (jwt/sign username (:jwt-key secrets) {:alg :hs256}))

(defn jwt-unsign [token]
  (jwt/unsign token (:jwt-key secrets) {:alg :hs256}))