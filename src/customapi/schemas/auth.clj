(ns customapi.schemas.auth)

(def LoginParams
  {:username string? :password string?})

(def LoginResponse
  {:token string?})

(def AuthParam
  {:auth-token string?})

(def AuthResponse
  {:username string?})

(def Error
  {:error string?})