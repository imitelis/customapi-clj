(ns customapi.schemas.secure)

(def ErrorResponse
  [:map
   [:error string?]])

(def MessageResponse
  [:map
   [:message string?]])