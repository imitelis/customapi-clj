(ns customapi.handlers.secure)

(defn secure-handler [_]
  {:status 201 :body {:message "I'm a secret!"}})