(ns my-worker.core
  (:require [clojureworker.core :as clfl])
  (:require-macros [my-worker.helpers :as helpers]))


(def routes [
  (clfl/route "GET" "/" (helpers/static-file "static/index.html"))
  (clfl/route "GET" "/index.html" (helpers/static-file "static/index.html"))
  (clfl/route "GET" "/api/map-as-json" {:hello 1})
  (clfl/route "POST" "/api/ping" #(identity {:body (:body %)
                                             :headers {}
                                             :status 200}))])

(apply clfl/simulate-worker
       {:method "GET" :path "/api/string-as-html" :headers {} :body nil}
       routes)

;; use worker to connect the routes to the worker environment
(apply clfl/worker routes)