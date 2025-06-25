(ns github-user-activity.fun_core)

(defn get-username [args]
  (if (empty? args)
    (do
      (println "Usage: github-activity <username>"))
    (first args)))
