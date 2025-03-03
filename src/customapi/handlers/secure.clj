(ns customapi.handlers.secure)

(def secure-handler (fn [request]
                       ;; In a real app authentication would be handled by middleware
   (if (= "secret" (get-in request [:headers "example-api-key"]))
     {:status 200
      :body {:secret "I am a marmot"}}
     {:status 401
      :body {:error "unauthorized"}})))