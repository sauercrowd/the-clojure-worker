(ns my-worker.helpers)

(defmacro static-file [fname] (slurp fname))