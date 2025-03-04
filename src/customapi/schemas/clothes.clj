(ns customapi.schemas.clothes)

(def cloth
  [:map
   [:uuid string?]
   [:name string?]
   [:type string?]
   [:size number?]])

(def cloth-without-uuid
  [:map
   [:name string?]
   [:type string?]
   [:size number?]])

(def clothes-query
  [:map
   [:clothes-name {:optional true} [:maybe string?]]
   [:clothes-type {:optional true} [:maybe string?]]])

(def clothes
  [:vector cloth])