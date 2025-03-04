(ns customapi.schemas.secure)

(def error-response
  [:map
   [:error string?]])

(def secret-response
  [:map
   [:message string?]])