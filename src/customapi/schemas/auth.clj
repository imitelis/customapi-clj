(ns customapi.schemas.auth)

(def login-params
  {:username string? :password string?})

(def login-response
  {:token string?})

(def auth-param
  {:auth-token string?})

(def auth-response
  {:username string?})

(def error
  {:error string?})