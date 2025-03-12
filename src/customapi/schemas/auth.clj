(ns customapi.schemas.auth)

(def LoginParams
  [:map
   [:username string?]
   [:password string?]])

(def LoginResponse
  [:map
   [:token string?]])

(def AuthParam
  [:map
   [:auth-token string?]])

(def AuthResponse
  [:map
   [:username string?]])

(def ErrorResponse
  [:map
   [:error string?]])