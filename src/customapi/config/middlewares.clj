(ns customapi.config.middlewares
  (:require
   [customapi.config.secrets :refer [secrets]]
   [muuntaja.core :as muuntaja-core]
   [reitit.coercion.spec :as coercion-spec]
   [reitit.dev.pretty :as pretty]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.multipart :as multipart]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.swagger :as swagger]
   [ring.logger :as logger]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.middleware.json :as json]))

(defn authentication-middleware [handler]
  (fn [request]
    (let [api-key (get-in request [:headers "api-key"])]
      (if (= api-key "secret")
        (handler request)
        {:status 401 :error "Unauthorized"}))))

(defn wrap-cors-middleware [handler]
  (wrap-cors handler
             :access-control-allow-origin (:allowed-origin secrets)
             :access-control-allow-host (:allowed-host secrets)
             :access-control-allow-methods [:get :post :patch :delete]))

(def set-middlewares
  [;; swagger feature
   swagger/swagger-feature
   ;; query-params & form-params
   parameters/parameters-middleware
   ;; content-negotiation
   muuntaja/format-negotiate-middleware
   ;; encoding response body
   muuntaja/format-response-middleware
   ;; coercing response body
   coercion/coerce-response-middleware
   ;; coercing request body
   coercion/coerce-request-middleware
   ;; exception handling
   exception/exception-middleware
   ;; decoding request body
   muuntaja/format-request-middleware
   ;; logger wrapper
   logger/wrap-with-logger
   ;; json body wrapper
   json/wrap-json-body
   ;; cors wrapper
   wrap-cors-middleware
   ;; multipart middleware
   multipart/multipart-middleware])

(def use-middlewares
  {;;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
   ;;:validate spec/validate ;; enable spec validation for route data
   ;;:reitit.spec/wrap spell/closed ;; strict top-level validation
   :exception pretty/exception
   :data {:coercion coercion-spec/coercion
          :muuntaja muuntaja-core/instance
          :middleware set-middlewares}})