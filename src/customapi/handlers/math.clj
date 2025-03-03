(ns customapi.handlers.math)

(def plus-query-handler
  (fn [{{{:keys [x y]} :query} :parameters}]
    {:status 200
     :body {:total (+ x y)}}))

(def plus-body-handler
  (fn [{{{:keys [x y]} :body} :parameters}]
    {:status 200
     :body {:total (+ x y)}}))

