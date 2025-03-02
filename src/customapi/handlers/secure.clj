(ns customapi.handlers.secure)

(defn get-message-handler [_]
  {:status 201 :body {:message "secret message"}})