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
  {:clothes-name string?
   :clothes-type string?})