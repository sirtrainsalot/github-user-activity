(ns github-user-activity.impshell
  (:require [github-user-activity.fun_core :refer [get-username]])
  (:gen-class))

(defn -main [& args]
  (let [username (get-username args)]
    (if (empty? username)
      (println "Empty username - start over")
      (println "Lets find some data for" username)))
  )



