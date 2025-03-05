(ns customapi.schemas.secure)

(def error-response
  [:map
   [:error string?]])

(def message-response
  [:map
   [:message string?]])