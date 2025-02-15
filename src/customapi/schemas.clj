(ns customapi.schemas)

(def clothes-schema
  [:map
   [:id :int?]
   [:name :string]
   [:type :string]
   [:size :string]])