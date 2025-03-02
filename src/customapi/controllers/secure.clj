(ns customapi.controllers.secure)

(defn get-message-controller [_]
  {:status 201 :body {:message "secret message"}})