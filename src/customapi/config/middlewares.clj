(ns customapi.config.middlewares
  (:require [malli.util :as mu]
            [muuntaja.core :as mc]
            [reitit.coercion.malli :as coercion-malli]
            ;; [spec-tools.spell :as spell]
            [reitit.dev.pretty :as pretty]
            [reitit.openapi :as openapi]
            [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.multipart :as multipart]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]
            ;; [reitit.ring.middleware.dev :as dev]
            [reitit.ring.spec :as ring-spec]
            [reitit.swagger :as swagger]
            [ring.logger :as logger]
            [ring.middleware.cors :as cors]
            [customapi.config.secrets :refer [secrets]]))

(defn wrap-cors-middleware [handler]
  (cors/wrap-cors handler
             :access-control-allow-origin (:allowed-origin secrets)
             :access-control-allow-host (:allowed-host secrets)
             :access-control-allow-methods [:get :post :patch :delete]))

(def middlewares
  {;;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
   :validate ring-spec/validate ;; enable spec validation for route data
   ;;:reitit.spec/wrap spell/closed ;; strict top-level validation
   :exception pretty/exception
   :data {:coercion
          (coercion-malli/create
           {;; set of keys to include in error messages 
            :error-keys #{#_:type :coercion :in :schema :value :errors :humanized #_:transformed}
            ;; schema identity function (default: close all map schemas)
            :compile mu/closed-schema
            ;; strip-extra-keys (affects only predefined transformers)
            :strip-extra-keys true
            ;; add/set default values
            :default-values true
            ;; malli options
            :options nil})
          :muuntaja mc/instance
          :middleware
          [;; swagger feature
           swagger/swagger-feature
           ;; openapi feature
           openapi/openapi-feature
           ;; query-params & form-params
           parameters/parameters-middleware
           ;; content-negotiation
           muuntaja/format-negotiate-middleware
           ;; encoding response body
           muuntaja/format-response-middleware
           ;; exception handling
           exception/exception-middleware
           ;; decoding request body
           muuntaja/format-request-middleware
           ;; coercing response bodys
           coercion/coerce-response-middleware
           ;; coercing request parameters
           coercion/coerce-request-middleware
           ;; multipart middleware
           multipart/multipart-middleware
           ;; logger wrapper
           logger/wrap-with-logger
           ;; cors wrapper
           wrap-cors-middleware]}})

