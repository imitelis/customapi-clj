(ns customapi.integration.files-test
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.server :refer [app]]
            [ring.mock.request :refer [json-body request]]))


(deftest files-test
  #_((testing "Download"
       (is (= (-> {:request-method :get :uri "/files/download"}
                  app :body (#(slurp % :encoding "ascii")) count)  ;; binary
              (.length (clojure.java.io/file "resources/reitit.png"))
              506325)))

     (testing "Upload"
       (let [file (clojure.java.io/file "resources/reitit.png")
             multipart-temp-file-part {:tempfile file
                                       :size (.length file)
                                       :filename (.getName file)
                                       :content-type "image/png;"}]
         (is (= (-> {:request-method :post :uri "/files/data" :multipart-params {:file multipart-temp-file-part}}
                    app :body slurp)
                "{\"name\":\"reitit.png\",\"size\":506325}")))))
  )