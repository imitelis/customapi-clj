(ns customapi.routes.math)

(def math-routes ["/math"
                  {:tags #{"math"}}

                  ["/plus"
                   {:get {:summary "plus with malli query parameters"
                          :parameters {:query [:map
                                               [:x
                                                {:title "X parameter"
                                                 :description "Description for X parameter"
                                                 :json-schema/default 42}
                                                int?]
                                               [:y int?]]}
                          :responses {200 {:body [:map [:total int?]]}}
                          :handler (fn [{{{:keys [x y]} :query} :parameters}]
                                     {:status 200
                                      :body {:total (+ x y)}})}
                    :post {:summary "plus with malli body parameters"
                           :parameters {:body [:map
                                               [:x
                                                {:title "X parameter"
                                                 :description "Description for X parameter"
                                                 :json-schema/default 42}
                                                int?]
                                               [:y int?]]}
                           :responses {200 {:body [:map [:total int?]]}}
                           :handler (fn [{{{:keys [x y]} :body} :parameters}]
                                      {:status 200
                                       :body {:total (+ x y)}})}}]])